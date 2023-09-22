package dream.profile.service;

import dream.auction.domain.Auction;
import dream.auction.domain.AuctionQueryRepository;
import dream.card.domain.DreamCard;
import dream.card.domain.DreamCardQueryRepository;
import dream.card.domain.DreamCardRepository;
import dream.common.domain.BaseCheckType;
import dream.common.domain.ResultTemplate;
import dream.common.exception.BadRequestException;
import dream.common.exception.NotFoundException;
import dream.profile.dto.response.*;
import dream.user.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NightProfileService {


    private final FollowRepository followRepository;
    private final DreamCardRepository dreamCardRepository;
    private final DreamCardQueryRepository dreamCardQueryRepository;
    private final UserRepository userRepository;
    private final AuctionQueryRepository auctionQueryRepository;

    private final ProfileService profileService;

    public ResultTemplate getNightHeader(User user, Long profileUserId) {

        User profileUser = userRepository.findByUserId(profileUserId).orElseThrow(() -> {
            throw new BadRequestException(BadRequestException.NOT_EXIST_USER_PROFILE);
        });


        int followingCount = followRepository.findByFromId(profileUser.getUserId()).size();
        int followerCount = followRepository.findByToId(profileUser.getUserId()).size();

        //내 프로필 헤더 조회
        if (user.getUserId() == profileUserId) {
            return profileService.getHeaderBySelf(profileUser);

        } else {
            int dreamCardCount = dreamCardRepository.findByDreamCardOwnerId(profileUserId).size();
            ResponseNightProfileHeaderByOther response = ResponseNightProfileHeaderByOther.from(profileUser, dreamCardCount, followerCount, followingCount);
            return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();
        }
    }

    public ResultTemplate getProfileCardTabList(User user, Long profileId, Long lastItemId, int size) {


        User profileUser = userRepository.findByUserId(profileId).orElseThrow(() -> {
            throw new BadRequestException(BadRequestException.NOT_EXIST_USER_PROFILE);
        });

        List<DreamCard> dreamCards = dreamCardQueryRepository.findDreamCardByOwnerIdPaging(profileId, lastItemId, size);
        if (dreamCards.size() == 0) throw new NotFoundException(NotFoundException.CARD_LIST_NOT_FOUND);

        List<ResponseProfileDreamCardTab> list;
        if (user.getUserId() == profileId) {
            list = dreamCards.stream()
                    .limit(size)
                    .map(dreamCard -> {
                        return ResponseProfileDreamCardTab.from(dreamCard);
                    }).limit(size).collect(Collectors.toList());

        } else {
            list = dreamCards.stream()
                    .filter(dreamCard -> dreamCard.getIsShow().equals(BaseCheckType.T))
                    .map(dreamCard -> {
                        return ResponseProfileDreamCardTab.from(dreamCard);
                    }).limit(size).collect(Collectors.toList());
        }


        boolean hasNext = dreamCards.size() > size;

        ResponseProfileDreamCardTabList response = ResponseProfileDreamCardTabList.from(list, hasNext);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();


    }


    public ResultTemplate getProfileAuctionOnSaleList(User user, Long lastItemId, int size){

        List<Auction> auctions =  auctionQueryRepository.findAuctionOnSaleListByDreamCardOwner(user.getUserId(), lastItemId, size);

        List<ResponseProfileAuction> list = auctions.stream()
                .map(auction -> {
                    return ResponseProfileAuction.from(auction);
                }).limit(size).collect(Collectors.toList());

        boolean hasNext = auctions.size() > size;

        ResponseProfileAuctionList response = ResponseProfileAuctionList.from(list, hasNext);
        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();

    }

    public ResultTemplate getProfileParticipatedAuctionList(User user, Long lastItemId, int size){


        List<Auction> auctions = auctionQueryRepository.findParticipatedAuctionListByDreamCardOwner(user.getUserId(), lastItemId, size);

        List<ResponseProfileAuction> list = auctions.stream()
                .map(auction -> {
                    return ResponseProfileAuction.from(auction);
                }).limit(size).collect(Collectors.toList());

        boolean hasNext = auctions.size() > size;

        ResponseProfileAuctionList response = ResponseProfileAuctionList.from(list, hasNext);
        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();

    }


}
