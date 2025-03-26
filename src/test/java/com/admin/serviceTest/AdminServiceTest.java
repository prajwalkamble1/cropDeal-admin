package com.admin.serviceTest;



import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.admin.dto.AdminDTO;
import com.admin.exception.InvalidAdminException;
import com.admin.model.Admin;
import com.admin.repository.AdminRepository;
import com.admin.service.AdminService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

public class AdminServiceTest {

    @Mock
    private AdminRepository adminRepository; 

    @InjectMocks
    private AdminService adminService; 

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); 
    }

    @Test
    public void testFindById_ValidId() throws InvalidAdminException {
        // Arrange
        Admin admin = new Admin();
        admin.setId(1);
        admin.setName("John Doe");
        admin.setUserName("johndoe");
        when(adminRepository.findById(1)).thenReturn(Optional.of(admin));

        // Act
        Admin result = adminService.findById(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("John Doe", result.getName());
        verify(adminRepository, times(1)).findById(1); // Verify the interaction with the mock
    }

    @Test
    public void testFindById_InvalidId() {
        // Arrange
        when(adminRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        InvalidAdminException thrown = assertThrows(InvalidAdminException.class, () -> {
            adminService.findById(999);
        });
        assertEquals("Invalid Id", thrown.getMessage());
    }

    @Test
    public void testAddAdmin() {
        // Arrange
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setName("Jane Doe");
        adminDTO.setUserName("janedoe");
        adminDTO.setEmail("jane@example.com");
        adminDTO.setPassword("password");

        Admin admin = new Admin();
        admin.setName(adminDTO.getName());
        admin.setUserName(adminDTO.getUserName());
        admin.setEmail(adminDTO.getEmail());
        admin.setPassword(adminDTO.getPassword());

        when(adminRepository.save(any(Admin.class))).thenReturn(admin);

        // Act
        Admin result = adminService.addAdmin(adminDTO);

        // Assert
        assertNotNull(result);
        assertEquals("Jane Doe", result.getName());
        assertEquals("janedoe", result.getUserName());
        verify(adminRepository, times(1)).save(any(Admin.class)); // Verify that save was called
    }

    @Test
    public void testDeleteById_ValidId() {
        // Arrange
        doNothing().when(adminRepository).deleteById(1); // Mock the behavior for deleteById
        // Act
        Admin result = adminService.deleteById(1);

        // Assert
        assertNull(result); // Since the method returns null after deletion
        verify(adminRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteById_InvalidId() {
        // Arrange
        doThrow(new InvalidAdminException("Invalid Id")).when(adminRepository).deleteById(999);

        // Act & Assert
        InvalidAdminException thrown = assertThrows(InvalidAdminException.class, () -> {
            adminService.deleteById(999);
        });
        assertEquals("Invalid Id", thrown.getMessage());
    }

    @Test
    public void testAuthAdmin_ValidUserName() throws InvalidAdminException {
        // Arrange
        Admin admin = new Admin();
        admin.setUserName("johndoe");
        when(adminRepository.findByUserName("johndoe")).thenReturn(Optional.of(admin));

        // Act
        Admin result = adminService.authAdmin("johndoe");

        // Assert
        assertNotNull(result);
        assertEquals("johndoe", result.getUserName());
        verify(adminRepository, times(1)).findByUserName("johndoe");
    }

    @Test
    public void testAuthAdmin_InvalidUserName() {
        // Arrange
        when(adminRepository.findByUserName("nonexistentuser")).thenReturn(Optional.empty());

        // Act & Assert
        InvalidAdminException thrown = assertThrows(InvalidAdminException.class, () -> {
            adminService.authAdmin("nonexistentuser");
        });
        assertEquals("No admin found", thrown.getMessage());
    }
}
