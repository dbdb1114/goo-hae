
const addOptionButton = document.getElementById("addOptionValue");

const tabButtonOfProductInfomation = document.getElementById("productInformationTabButton");
const tabButtonOfproductDetail = document.getElementById("productDetailTabButton");

const tabProductInfomation = document.getElementById("inputContainerBox");
const tabProductDetail = document.getElementById("productDetailTab");

const uploadImgButtonList = document.getElementsByClassName("imgUploadButton");

/**
 * 카테고리 설정 이벤트
 * - 대분류에 맞는 카테고리 노출
 * */
const largeCategory = document.getElementById("largeCategory");
const mediumCategory = document.getElementById("mediumCategory");

largeCategory.addEventListener('change',()=>{

    const largeCategoryName = largeCategory[largeCategory.selectedIndex].value;

    switch (largeCategoryName) {
        case 'livingRoom':
            mediumCategory.innerHTML = `<option value="sofa" selected>소파</option>
            <option value="table">테이블</option>
            <option value="livingroomChair">의자-거실</option>
            <option value="tvConsole">TV콘솔</option>`
            break;
        case 'bedRoom':
            mediumCategory.innerHTML = ` <option value="bed" selected>침대</option>
                            <option value="mattress">매트리스</option>
                            <option value="nightstand">협탁</option>`
            break;
        case 'kitchen':
            mediumCategory.innerHTML = `<option value="diningTable" selected>식탁</option>
                            <option value="kitchenChair">의자</option>`
            break;
        case 'dressRoom':
            mediumCategory.innerHTML = `<option value="hanger" selected>행거</option>
                            <option value="storageCabinet">수납장</option>
                            <option value="dressingTable">화장대</option>`
            break;
    }
})

/***
 * 탭버튼
 */
tabButtonOfProductInfomation.addEventListener('click',(e)=>{
    e.target.setAttribute('style','font-weight : bold;');
    tabButtonOfproductDetail.setAttribute('style','font-weight : none;');
    tabProductInfomation.classList.remove('displayNone');
    tabProductInfomation.classList.add('displayFlex');
    tabProductDetail.classList.remove('displayFlex');
    tabProductDetail.classList.add('displayNone');
})

tabButtonOfproductDetail.addEventListener('click', (e)=>{
    e.target.setAttribute('style','font-weight : bold;');
    tabButtonOfProductInfomation.setAttribute('style','font-weight : none;');
    tabProductInfomation.classList.remove('displayFlex');
    tabProductInfomation.classList.add('displayNone')
    tabProductDetail.classList.remove('displayNone');
    tabProductDetail.classList.add('displayFlex');
})


addOptionButton.addEventListener('click',()=>{
    const id = crypto.randomUUID();

    let newOptionImageWrapper = document.createElement('div');
    let optionImageWrapper = document.getElementsByClassName('optionImageWrapper')[0];
    let optionNameInput = document.createElement('input');
    let imgInput = document.createElement('input');
    let label = document.createElement('label');

    newOptionImageWrapper.setAttribute('class', 'optionImageWrapper');
    optionNameInput.setAttribute('type', 'text');

    imgInput.setAttribute('id','optionImg' + id);
    imgInput.setAttribute('name', 'optionImg' + id);
    imgInput.setAttribute('type','file');
    imgInput.setAttribute('style', 'display: none;');

    label.setAttribute('class', 'imageButtonLabel')
    label.setAttribute('for', 'optionImg' + id)
    label.innerHTML = '+';

    newOptionImageWrapper.appendChild(optionNameInput);
    newOptionImageWrapper.appendChild(imgInput);
    newOptionImageWrapper.appendChild(label);

    optionImageWrapper.after(newOptionImageWrapper);
})


$(document).ready(function() {
    $('#summernote').summernote({
        callbacks: {
            // onImageUpload: (files)=>insertImage(files[0],this)
        },
        toolbar: [
            ['style', ['bold', 'italic', 'underline', 'clear']],
            ['fontsize', ['fontsize']],
            ['color', ['color']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['insert', ['picture','video']],
        ],
        height: 55,
        focus : true
    });
});

// 이미지 체크 함수
// 파일명을 통해 확장자명을 확인
function imageCheck(fileName) {
    const fileTypeIdx = fileName.lastIndexOf('.');
    const fileType = fileName.substring(fileTypeIdx+1);
    console.log(fileType)

    if(fileType.toLowerCase().trim() != "png"){
        return false;
    } else {
        return true;
    }
}

for (let i = 0; i < uploadImgButtonList.length; i++) {
    uploadImgButtonList[i].addEventListener('change', (e)=>{
        const target = e.target;
        if(imageCheck(target.value)){
            let preImg = target.files[0];

            //fileReader객체 생성
            const fileReader = new FileReader();

            //fileReader객체가 load될때 아래의 함수 실행
            fileReader.onload = () => {
                let img = document.createElement("img");
                img.setAttribute("src", fileReader.result);
                img.setAttribute("class", "previewImage");
                target.after(img);
            }

            //fileReader의 binary-data를 읽어냄
            fileReader.readAsDataURL(preImg);
        } else {
            target.value = '';
            window.alert("이미지 파일 형식을 지켜주세요.")
        }
    })
}


