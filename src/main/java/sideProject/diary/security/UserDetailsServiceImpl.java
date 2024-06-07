package sideProject.diary.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sideProject.diary.member.MemberEntity;
import sideProject.diary.member.MemberRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberEntity user = memberRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return user;
    }

}
