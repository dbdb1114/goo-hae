const uploadImg = document.querySelector("#uploadImg");
const preview = document.querySelector(".preview");

const imgPreview = () => {
    //이미지 추가할때 preview하위에 있는 태그를 전부 없애줌
    preview.innerHTML = '';
    let imgs = uploadImg.files;

    for (let i = 0; i < imgs.length; i++) {
        //imgs가 fileList 타입의 객체이므로 imgs를 인덱싱하여 안에있는 Blob타입의 배열요소를 가져옴
        let preImg = imgs[i];

        //fileReader객체 생성
        const fileReader = new FileReader();

        //fileReader객체가 load될때 아래의 함수 실행
        fileReader.onload = () => {
            let img = document.createElement("img");
            img.setAttribute("src", fileReader.result);
            preview.appendChild(img);
        }
        //fileReader의 binary-data를 읽어냄
        fileReader.readAsDataURL(preImg);
    }
};

uploadImg.addEventListener('change', imgPreview);