package sideProject.diary.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void saveMember(MemberDto dto){
        MemberEntity saved = memberRepository.save(MemberDto.dtoToEntity(dto));
    }

}
