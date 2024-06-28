package sideProject.diary.member;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sideProject.diary.jwt.JwtDto;
import sideProject.diary.jwt.JwtRepository;
import sideProject.diary.jwt.JwtService;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final JwtRepository jwtRepository;

    private final JwtService jwtService;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final EntityManager entityManager;

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

    public JwtDto login(String email, String password){
        // 1. email + password 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        // 2. 실제 검증. authenticate가 실행되며 loadUserByUsername 실행.
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 검증된 authentication 객체로 JWT 생성.
        JwtDto jwtDTO = jwtService.generateToken(authentication);

        // 4. RefreshToken DB 저장.
        jwtDTO.setEmail(email);
        jwtRepository.save(JwtDto.dtoToEntity(jwtDTO));

        return jwtDTO;
    }

    @Transactional
    public MemberDto saveMember(MemberDto dto){
        MemberEntity saved = memberRepository.save(MemberDto.dtoToEntity(dto));
        return MemberDto.EntityToDto(saved);
    }

    public MemberDto findMemberByEmail(String email){
        return MemberDto.EntityToDto(memberRepository.findById(email).orElseThrow());
    }

}
