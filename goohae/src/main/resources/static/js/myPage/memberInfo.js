const form = document.querySelector('.memberInfoForm');

form.addEventListener('submit', function(event) {
  event.preventDefault(); // 폼 기본 제출 행동 방지

  // 회원 정보 유효성 검사
  if (!validateMemberInfoForm()) {
    return;
  }

  const formData = new FormData(form);

  // Ajax 요청
  const xhr = new XMLHttpRequest();
  xhr.open('POST', 'logined-user/myinfo/update'); // PUT 메서드로 회원 정보 수정 API 요청
  xhr.setRequestHeader('Content-Type', 'application/json');
  xhr.onreadystatechange = function() {
    if (xhr.readyState === XMLHttpRequest.DONE) {
      if (xhr.status === 200) {
        console.log(xhr.response); // 회원 정보 수정 성공시 서버로부터 받은 응답 데이터 출력
      } else {
        console.error(xhr.status); // 회원 정보 수정 실패시 오류 메시지 출력
      }
    }
  };
  xhr.send(JSON.stringify(formData.entries()));


//  const url = '/logined-user/myinfo/update';
//  const requestData = Object.fromEntries(formData.entries());
//
//  fetch(url, {
//    method: 'PATCH',
//    headers: {
//      'Content-Type': 'application/json',
//    },
//    body: JSON.stringify(requestData)
//  })
//    .then(response => {
//      if (!response.ok) {
//        throw new Error(response.status);
//      }
//      return response.json();
//    })
//    .then(data => {
//      console.log(data); // 회원 정보 수정 성공시 서버로부터 받은 응답 데이터 출력
//    })
//    .catch(error => {
//      console.error(error); // 회원 정보 수정 실패시 오류 메시지 출력
//    });
//});

});

function validateMemberInfoForm() {
  // 비밀번호 유효성 검사
  const password = document.querySelector('.memberInfoPwInput').value;
  const passwordConfirm = document.querySelector('.memberInfoPwConfirmInput').value;
  if (password.length < 8 || password.length > 16) {
    alert('비밀번호는 8~16자 이내로 입력해주세요.');
    return false;
  } else if (password !== passwordConfirm) {
    alert('비밀번호가 일치하지 않습니다.');
    return false;
  }

  // 이름 유효성 검사
  const name = document.querySelector('.memberInfoNameInput').value;
  if (name.trim() === '') {
    alert('이름을 입력해주세요.');
    return false;
  }

  // 휴대전화 번호 유효성 검사
  const phone = document.querySelector('.memberInfoTellInput').value;
  if (!/^[0-9]{3}-[0-9]{3,4}-[0-9]{4}$/.test(phone)) {
    alert('휴대전화 번호를 올바른 형식으로 입력해주세요.');
    return false;
  }

  // 이메일 수신여부 체크 확인
  const emailGet = document.querySelector('input[name="EmailGet"]:checked');
  if (!emailGet) {
    alert('이메일 수신여부를 선택해주세요.');
    return false;
  }

  // 유효성 검사 통과
  return true;
}