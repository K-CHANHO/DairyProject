package sideProject.diary.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberEntity user = memberRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return user;
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
