
Spring Boot Application:

Controller Class:

@RestController
public class PasswordResetController {

	@Autowired
	private PasswordResetService passwordResetService;

	@GetMapping("/forgot-password")
	public ResponseEntity<String> forgotPassword() {
		return new ResponseEntity<>("Enter your registered email address to get a password reset link through email", HttpStatus.OK);
	}

	@PostMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@RequestBody PasswordResetRequest passwordResetRequest) {
		if (passwordResetService.isEmailRegistered(passwordResetRequest.getEmail())) {
			passwordResetService.sendPasswordResetLink(passwordResetRequest.getEmail());
			return new ResponseEntity<>("Password reset link sent to your registered email address", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Email address not registered", HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/reset-password")
	public ResponseEntity<String> confirmPasswordReset(@RequestBody PasswordResetConfirmation passwordResetConfirmation) {
		if (passwordResetService.resetPassword(passwordResetConfirmation.getOldPassword(),
				passwordResetConfirmation.getNewPassword(), passwordResetConfirmation.getConfirmPassword())) {
			return new ResponseEntity<>("Password reset successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Password reset failed", HttpStatus.BAD_REQUEST);
		}
	}

}

Service Class:

@Service
public class PasswordResetService {

	@Autowired
	private UserRepository userRepository;

	public boolean isEmailRegistered(String email) {
		User user = userRepository.findOneByEmail(email);
		return user != null;
	}

	public void sendPasswordResetLink(String email) {
		// send password reset link to the email
	}

	public boolean resetPassword(String oldPassword, String newPassword, String confirmPassword) {
		// validation for old password, new password, and confirm password
		if (!newPassword.equals(confirmPassword)) {
			return false;
		}
		// reseting the password
		return true;
	}

}

Repository Class:

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findOneByEmail(String email);

}