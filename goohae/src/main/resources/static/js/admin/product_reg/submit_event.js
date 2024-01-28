
const submitButton = document.getElementById("regSubmitButton");

const largeCategoryBox = document.getElementById("largeCategory");
const mediumCategoryBox = document.getElementById("mediumCategory");

const inputProductName = document.getElementById("productName");


const productName = inputProductName.value;
const categoryName = largeCategoryBox[largeCategoryBox.selectedIndex].value + "/"
    + mediumCategoryBox[mediumCategoryBox.selectedIndex].value;

/**
 * 1. 디테일 페이지 이미지 가져오기
 *  나중에 에디터를 바꿨을 때도 바로 바꿀 수 있도록 확장성을 가져야함.
 *  -1. 해당 이미지 업로드 후 img 태그로 변경해서 넣어야함.
 *  -2. 현재 summernote라이브러리 내부 이미지 업로드가 에디터에 인서트 직후 일어나므로, 좋지 않음
 *  -3. 업로드 후 해당 이미지 src를 모두 받아서 다시 수정해주는 작업이 필요함.
 *  -4. 이것이 끝.
 *
 * */

submitButton.addEventListener('click', ()=>{
    console.log("등록 클릭 이벤트 발생 ------")
    /** 상세페이지 이미지 업로드
     *  imgTagInEditor : 이미지 태그
     *  detailPageImages : 이미지 파일객체
     * */
    const imgTagInEditor = getImgTagInEditor();
    const detailPageImages = uploadEditorImage(imgTagInEditor);

    insertImages(detailPageImages)
        .then((res)=>{
            for (let i = 0; i < imgTagInEditor.length; i++ ){
                imgTagInEditor[i].src = res[`${imgTagInEditor[i].id}`];
            }
        })
        .catch((err) => console.log(err));
})


/** *
 *
 * @param base_data
 * @param filename
 * @returns {File}
 */
function base64toFile(base_data, filename) {

    let arr = base_data.split(','),
        mime = arr[0].match(/:(.*?);/)[1],
        bstr = atob(arr[1]),
        n = bstr.length,
        u8arr = new Uint8Array(n);

    while(n--){
        u8arr[n] = bstr.charCodeAt(n);
    }

    return new File([u8arr], filename, {type:mime});
}

/***
 * @returns {NodeListOf<Element>}
 */
function getImgTagInEditor () {
    return document.querySelectorAll('.note-editing-area img');
}


/**
 * @param imgTagInEditor
 * @returns {*[]}
 */
function uploadEditorImage(imgTagInEditor){

    let imageFiles = [];

    for (let i = 0; i < imgTagInEditor.length; i++ ){
        const file = base64toFile(imgTagInEditor[i].src, productName + "#detail_image"+(i+1));
        imgTagInEditor[i].id = "detail_image"+(i+1);
        imageFiles.push(file);
    }

    return imageFiles;
}


/***
 * @param files
 * @returns {Promise<any>}
 */
function insertImages(files){
    let formData = new FormData();
    formData.append("categoryName", categoryName)
    for (let i = 0; i < files.length; i++) {
        formData.append("detailPageImages", files[i]);
    }

    const option = {
        method: 'post',
        body: formData
    }

    return fetch('/cloudinary/admin/upload/detail-images',{...option})
        .then(res => res.json())
}
