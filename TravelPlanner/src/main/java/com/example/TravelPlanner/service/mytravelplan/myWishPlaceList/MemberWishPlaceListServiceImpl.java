package com.example.TravelPlanner.service.mytravelplan.myWishPlaceList;

import com.example.TravelPlanner.domain.MemberWishPlace;
import com.example.TravelPlanner.dto.myplan.MyWishPlaceListDto;
import com.example.TravelPlanner.repository.MemberWishPlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
// 나중에 nosql db도 사용하면 조회속도를 위해서 nosql db에 넘긴다음 조회하는 걸로 바꿔도 나쁘지 않을듯
public class MemberWishPlaceListServiceImpl implements MemberWishPlaceListService {

    private final MemberWishPlaceRepository memberWishPlaceRepository;

    @Override
    @Transactional
    public boolean addWishPlace(String memberId, Long placeId) {
        if (!memberWishPlaceRepository.existsByMemberIdAndPlaceId(memberId, placeId)) {
            MemberWishPlace memberWishPlace = new MemberWishPlace(memberId, placeId);
            memberWishPlaceRepository.save(memberWishPlace);
            return true;
        }
        return false;
    }

    @Override
    public List<MyWishPlaceListDto> getWishList(String memberId) {
        List<MemberWishPlace> memberWishPlaces = memberWishPlaceRepository.findAllByMemberId(memberId);
        return memberWishPlaces.stream()
                .map(w -> new MyWishPlaceListDto(w.getMemberId(), w.getPlaceId()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean deleteWishPlace(String memberId, Long placeId) {
        if (memberWishPlaceRepository.existsByMemberIdAndPlaceId(memberId, placeId)) {
            memberWishPlaceRepository.deleteByMemberIdAndPlaceId(memberId, placeId);
            return true;
        }
        return false;
    }



}
