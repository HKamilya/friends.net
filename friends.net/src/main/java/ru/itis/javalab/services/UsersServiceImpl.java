package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.UserForm;
import ru.itis.javalab.model.Image;
import ru.itis.javalab.model.User;
import ru.itis.javalab.repositories.UsersRepository;
import ru.itis.javalab.util.EmailUtil;
import ru.itis.javalab.util.MailsGenerator;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private MailsGenerator mailsGenerator;

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${UPLOAD_DIR}")
    private String UPLOAD_DIR;

    @Autowired
    private ImagesService imagesService;

    @Value("${server.url}")
    private String serverUrl;

    @Value("${mail.username}")
    private String from;


    @Override
    public void update(User user) {
//        usersRepository.update(user);
    }


    @Override
    @Transactional
    public Optional<User> findById(Long id) {
        Optional<User> optional = usersRepository.findById(id);
        optional.ifPresent(user -> user.getEvents().iterator());
        optional.ifPresent(user -> user.getRequests().iterator());
        return optional;
    }


    @Override
    public void save(UserForm userForm) {
        String encodePass = passwordEncoder.encode(userForm.getPassword());
        User user = User.builder()
                .username(userForm.getUsername())
                .email(userForm.getEmail())
                .password(encodePass)
                .role(User.Role.USER)
                .build();
        Image image = new Image();
        image.setType("image/png");
        image.setAddress(UUID.randomUUID().toString() + userForm.getUsername() + "man.png");
        BufferedImage bImage = null;
        try {

            String url1 = UPLOAD_DIR + File.separator + "man.png";
            System.out.println(url1);

            File initialImage = new File(UPLOAD_DIR + File.separator + "man.png");
            bImage = ImageIO.read(initialImage);
            ImageIO.write(bImage, "jpg", new File(UPLOAD_DIR + File.separator + image.getAddress()));

        } catch (
                IOException e) {
            e.printStackTrace();
        }


        image = imagesService.insert(image);


        user.setImage(image);

        user.setState(User.State.NOT_CONFIRMED);
        user.setConfirmCode(UUID.randomUUID().toString());
        usersRepository.save(user);
        String confirmMail = mailsGenerator.getMailForConfirm(serverUrl, user.getConfirmCode());
        emailUtil.sendMail(user.getEmail(), "Регистрация", from, confirmMail);

    }

    @Override
    public User findByName(String name) {
        Optional<User> userOptional = usersRepository.findByUsername(name);
        return userOptional.orElseGet(userOptional::get);
    }


    @Override
    public User authenticateUser(User user) {
        return usersRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public Optional<User> findByUUID(String uuid) {
        return usersRepository.findByUUID(uuid);
    }

    @Override
    public void updateUUID(User user) {
        Optional<User> user2 = usersRepository.findById(user.getId());
        if (user2.isPresent()) {
            user2.get().setUUID(user.getUUID());
            usersRepository.save(user2.get());
        }
    }

    @Override
    public boolean checkState(String code) {
        Optional<User> userOptional = usersRepository.findByConfirmCode(code);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getState().toString().equals(User.State.NOT_CONFIRMED.toString())) {
                user.setState(User.State.CONFIRMED);
                usersRepository.save(user);
                return true;
            }
        }
        return false;
    }
}
