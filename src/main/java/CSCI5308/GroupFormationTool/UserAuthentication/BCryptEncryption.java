package CSCI5308.GroupFormationTool.UserAuthentication;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BCryptEncryption implements IPasswordEncryptor {

    private final BCryptPasswordEncoder encode;

    public BCryptEncryption() {
        this.encode = new BCryptPasswordEncoder();
    }

    @Override
    public String encoder(String password) {
        return encode.encode(password);
    }

    @Override
    public Boolean passwordMatch(String password, String encryptedPassword) {
        return encode.matches(password, encryptedPassword);
    }
}
