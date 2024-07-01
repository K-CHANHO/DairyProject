package sideProject.diary.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sideProject.diary.member.MemberEntity;
import sideProject.diary.member.MemberRepository;

@RequiredArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = memberRepository.findById(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return user;
    }

    private UserDetails createUserDetails(MemberEntity entity) {
        return User.builder()
                .username(entity.getEmail())
                .password(entity.getPassword())
                .roles(entity.getRoles().toArray(new String[0]))
                .build();
    }
}
