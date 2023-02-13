package barrans.devel.api.service;

import barrans.devel.api.api.dto.UserDTO;
import barrans.devel.api.exception.ValidationException;
import barrans.devel.api.model.Role;
import barrans.devel.api.model.User;
import barrans.devel.api.util.DateUtil;
import barrans.devel.api.util.UserUtil;
import io.quarkus.narayana.jta.runtime.TransactionConfiguration;
import io.vertx.core.json.JsonObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserService {
//
//    @Inject
//    UserRepository userRepository;
//    @Transactional
//    public User persistUser(@Valid User user) {
//        user.persist();
//        return user;
//    }
//
//
//    public List<User> getListAll(){
//        List<User> users = userRepository.findAll().list();
//        return users;
//    }
//
//    public User findUserById(Long id) {
//        return userRepository.findById(id);
//    }
//
//    @Transactional
//    public void deleteUser(Long id){
//        User user = userRepository.findById(id);
//        user.delete();
//    }
//
//    @Transactional
//    public void updateUser(Long id, JsonObject params){
//        User user = userRepository.findById(id);
//        user.email = params.getString("email");
//        user.username = params.getString("username");
//        user.mobile_phone_number = params.getString("mobile_phone_number");
//        userRepository.persist(user);
//    }

        Date date;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        public Response add(UserDTO request, Long userId ) throws ParseException {
            if (!User.isEmptyEmail(request.email)){
                throw new ValidationException("EMAIL_SUDAH_TERDAFTAR");
            }

            if (!UserUtil.isEmail(request.email)){
                throw new ValidationException("ALAMAT_EMAIL_SALAH");
            }

            if (!UserUtil.isNama(request.fullName)){
                throw new ValidationException("NAMA_TIDAK_VALID");
            }

            if (request.role == null ){
                throw new ValidationException("BAD_REQUEST_ROLE_NULL");
            }

            Optional<Role> roleOptional = Role.findByIdOptional(request.role);
            if (roleOptional.isEmpty()){
                throw new ValidationException("BAD_REQUEST_ROLE_NOT_FOUND");
            }

            User user = User.findById(userId);
            persistPengguna(request, user, roleOptional.get(),null);
            return Response.ok(new HashMap<>()).build();
        }

        @Transactional
        @TransactionConfiguration(timeout = 30)
        public User persistPengguna(UserDTO request, User user, Role roleId, Long userIdParam) throws ParseException {

            User createUser = null;
            if (userIdParam == null){
                createUser = new User();
                createUser.setStatus(1);
                createUser.setCreatedBy(user);
            } else {
                Optional<User> userOptional = User.findByIdOptional(userIdParam);
                if (userOptional.isEmpty()){
                    throw new ValidationException("USER_NOT_FOUND");
                }
                user = userOptional.get();
            }

            createUser.setFullName(request.fullName);
            createUser.setEmail(request.email);
            createUser.setPob(request.pob);
            createUser.setDob(DateUtil.stringToDate(request.dob));
            createUser.setRoleId(roleId);
            createUser.setPassword(request.password);
            createUser.setUpdatedBy(user);

            User.persist(createUser);
            return createUser;

        }
    }


