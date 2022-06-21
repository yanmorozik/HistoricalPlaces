package eu.morozik.historicalplaces.security;

import eu.morozik.historicalplaces.dao.UserDao;
import eu.morozik.historicalplaces.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("userDetailsServiceImpl")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userDao.findByCredentialLogin(login).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));

        List<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(/*"ROLE_" +*/ role.getNameRole()))
                .collect(Collectors.toList());

        return SecurityUser.fromUser(user,grantedAuthorities);
    }
}
