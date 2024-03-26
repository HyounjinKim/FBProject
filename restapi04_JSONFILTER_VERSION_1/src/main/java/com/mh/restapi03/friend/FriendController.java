package com.mh.restapi03.friend;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friend")
@RequiredArgsConstructor
@Tag(name = "Friend-Controller", description = "친구 조회 등록 수정 삭제")
public class FriendController {

    private final FriendService friendService;

    @Operation(summary = "친구 전체 목록보기",description = "친구 전체 정보를 조회할 수 있습니다.")
    @GetMapping()
    public ResponseEntity<List<Friend>> getAllFriends() {
        List<Friend> list = friendService.getAllFriends();

        if (list.isEmpty()) {
            // 결과가 없는 경우에 대한 처리를 수행합니다.
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{nickname}")
    @Operation(summary = "닉네임으로 친구 조회", description = "닉네임을 이용하여 친구를 조회합니다.")
    @Parameters(
            @Parameter(description = "조회하고자 하는 친구의 닉네임 입력하세요",
                    name = "nickname",
                    required = true)
    )
    public ResponseEntity<Friend> getFriendByNickname(@PathVariable String nickname) {
        Friend friend = friendService.getFriendByNickname(nickname);
        if (friend == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(friend);
    }

    @PostMapping()
    public ResponseEntity<Friend> addFriend(@RequestBody @Valid FriendDto friendDto) {
        ModelMapper mapper = new ModelMapper();
        Friend friend = mapper.map(friendDto, Friend.class);
        Friend dbfriend = friendService.addFriend(friend);
        return ResponseEntity.status(HttpStatus.CREATED).body(dbfriend);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFriend(@PathVariable int id){
        friendService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("삭제됨");
    }

    @PostMapping("/approve")
    @Operation(summary = "친구 승인 처리", description = "친구의 승인 여부를 추가합니다.")
    public ResponseEntity<Friend> addFriendApproval(@RequestBody FriendApprovalDto friendApprovalDto) {
        try {
            Friend friend = friendService.addFriendApproval(friendApprovalDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(friend);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/approve/{fidx}")
    @Operation(summary = "친구 승인 여부 수정", description = "친구의 승인 여부를 수정합니다.")
    @Parameters(
            @Parameter(description = "수정하고자 하는 친구의 고유 식별자(fidx)를 입력하세요",
                    name = "fidx",
                    required = true)
    )
    public ResponseEntity<Friend> updateFriendApproval(@PathVariable int fidx, @RequestBody FriendApprovalDto friendApprovalDto) {
        try {
            Friend friend = friendService.updateFriendApproval(fidx, friendApprovalDto);
            if (friend == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(friend);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}