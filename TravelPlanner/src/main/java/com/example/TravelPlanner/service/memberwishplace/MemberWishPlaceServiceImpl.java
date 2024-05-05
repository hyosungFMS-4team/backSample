package com.example.TravelPlanner.service.memberwishplace;

import com.example.TravelPlanner.domain.Member;
import com.example.TravelPlanner.domain.MemberWishPlace;
import com.example.TravelPlanner.domain.Place;
import com.example.TravelPlanner.dto.wishPlace.MemberWishPlaceDto;
import com.example.TravelPlanner.repository.MemberRepository;
import com.example.TravelPlanner.repository.MemberWishPlaceRepository;
import com.example.TravelPlanner.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberWishPlaceServiceImpl implements MemberWishPlaceService {

    private final MemberWishPlaceRepository memberWishPlaceRepository;
    private final MemberRepository memberRepository;
    private final PlaceRepository placeRepository;

    @Override
    @Transactional
    public boolean addWishPlace(String memberId, Long placeId) {

        Member member = memberRepository.findByMemberId(memberId).orElse(null);
        Place place = placeRepository.findByPlaceId(placeId).orElse(null);
        if (memberId == null || placeId == null) {
            return false; // memberId 또는 placeId가 null이면 실패로 처리
        }
        if (!memberWishPlaceRepository.existsByMemberMemberIdAndPlacePlaceId(memberId, placeId)) {
            MemberWishPlace memberWishPlace = new MemberWishPlace();
            memberWishPlace.setMember(member);
            memberWishPlace.setPlace(place);
            memberWishPlaceRepository.save(memberWishPlace);
            return true;
        }
        return false;
    }

    @Override
    public List<MemberWishPlaceDto> getWishList(String memberId) {
        List<MemberWishPlace> userWishPlaces = memberWishPlaceRepository.findAllByMemberMemberId(memberId);
        return userWishPlaces.stream()
                .map(w -> new MemberWishPlaceDto(w.getMember().getMemberId(), w.getPlace().getPlaceId()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean deleteWishPlace(String memberId, Long placeId) {
        if (memberWishPlaceRepository.existsByMember_MemberIdAndPlace_PlaceId(memberId, placeId)) {
            memberWishPlaceRepository.deleteByMemberMemberIdAndPlacePlaceId(memberId, placeId);
            return true; // 삭제 성공시 true 반환
        }
        return false; // 해당 멤버의 해당 장소가 없는 경우 삭제 실패로 false 반환
    }
}
