package ua.edu.nuos.lab5java.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.edu.nuos.lab5java.data.User;
import ua.edu.nuos.lab5java.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User findById(Integer id) {
        return userRepository
                .findById(id)
                .orElse(null);
    }

    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void deleteById(Integer id) {
        if (userRepository.existsById(id)) userRepository.deleteById(id);
    }

    @Transactional
    public void saveVacancy(Integer userId, Integer vacancyId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) return;

        boolean alreadySaved = user.getVacancies().stream()
                .anyMatch(vacancy -> vacancy.getId().equals(vacancyId));

        if (alreadySaved) {
            userRepository.deleteSavedVacancy(userId, vacancyId);
        } else {
            userRepository.addVacancy(userId, vacancyId);
        }
    }
}
