// 선택한 주소를 삭제하는 함수
function deleteSelectedAddresses() {
  const checkboxes = document.querySelectorAll('#myAddressTableBody .checkbox:checked');
  checkboxes.forEach((checkbox) => {
    const row = checkbox.closest('tr');
    row.parentNode.removeChild(row);
  });
}

// 기본 주소를 설정하는 함수
function setDefaultAddress(row) {
  // 체크박스의 checked 상태를 false로 변경
  row.querySelector('.checkbox').checked = false;

  // 다른 행에서 기본 주소로 설정된 것이 있다면 해제
  const defaultRows = document.querySelectorAll('#myAddressTableBody .myAddressTableRow[data-default="true"]');
  defaultRows.forEach((defaultRow) => {
    defaultRow.setAttribute('data-default', false);
    defaultRow.textContent = '-';
  });

  // 현재 행을 기본 주소로 설정
  row.setAttribute('data-default', true);
  row.querySelector('.myAddressTableTh[data-th-text="주소록 고정"]').textContent = '●';
}

// 선택 주소 삭제 버튼 클릭 시 실행되는 함수
document.querySelector('#myAddressDeleteButton').addEventListener('click', () => {
  deleteSelectedAddresses();
});

// 기본 배송지 저장 버튼 클릭 시 실행되는 함수
document.querySelector('#myAddressInputButton').addEventListener('click', () => {
  const rows = document.querySelectorAll('#myAddressTableBody .myAddressTableRow');
  rows.forEach((row) => {
    if (row.querySelector('.checkbox').checked) {
      // 선택한 행을 기본 주소로 설정
      setDefaultAddress(row);
    }
  });
});

// 주소 수정 버튼 클릭 시 실행되는 함수
document.querySelectorAll('.myAddressCorrection').forEach((button) => {
  button.addEventListener('click', () => {
    // 선택한 주소 정보를 가져와서 수정 폼에 채움
    const defaultFlag = button.dataset.default;
    const addressName = button.dataset.addressName;
    const address = button.dataset.address;
    const orderName = button.dataset.orderName;
    const tellNumber = button.dataset.tellNumber;
    const phoneNumber = button.dataset.phoneNumber;

    // 수정 폼에 주소 정보 채우기
    document.querySelector('#default').checked = defaultFlag === 'true';
    document.querySelector('#addressName').value = addressName;
    document.querySelector('#address').value = address;
    document.querySelector('#orderName').value = orderName;
    document.querySelector('#tellNumber').value = tellNumber;
    document.querySelector('#phoneNumber').value = phoneNumber;

    // 수정 폼 띄우기
    openAddressCorrectionForm();
  });
});