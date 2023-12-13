'use strict'


let allCheckBtn = document.getElementById("allCheck");
let checkBtn = document.getElementsByClassName("shoppingCartTableCheck");
let shoppinCartTableBtn = document.getElementsByClassName("shoppingCartTableBtn");
let shoppingCartAllOrderButton = document.querySelector(".shoppingCartAllOrderButton");
let shoppingCartCheckedOrderButton = document.querySelector(".shoppingCartCheckedOrderButton");
let productPrice = document.getElementsByClassName("thProductPrice");
let cartForm = document.getElementById("#cartForm");
let formDataProCode = document.getElementsByClassName("formDataProCode");
let formDataProPrice = document.getElementsByClassName("formDataProPrice");
let formDataProEa = document.getElementsByClassName("formDataProEa");

console.log(formDataProCode)
console.log(formDataProPrice)
console.log(formDataProEa)



console.log(productPrice)
console.log(checkBtn)
console.log(shoppingCartAllOrderButton)
console.log(shoppingCartCheckedOrderButton)


allCheckBtn.addEventListener('click',function (){

    for (let i = 0; i<=checkBtn.length; i++){
        checkBtn[i].checked = true;
    }

})

let productEaInput = document.getElementsByClassName("shoppingCartProductEa");
console.log(productEaInput);

for( let i = 0; i<productEaInput.length; i++){
    productEaInput[i].addEventListener("change", function(e){
        let productCode = e.target.getAttribute("productCode");
        let productEa = e.target.value;
        $.ajax("/logined-user/mycart/changeea",{
            type:"post",
            data : {
                productCode: productCode,
                productEa : productEa,
            }
        })
    })
}

for(let i=0; i<shoppinCartTableBtn.length; i++){
    shoppinCartTableBtn[i].addEventListener("click", function (e) {
        let productCode = e.target.getAttribute("productCode");
        $.ajax("/logined-user/mycart/delete",{
            type: "post",
            data:{
                productCode:productCode
            },
            success: (res)=>{
                window.location.reload(true);
            }
        })

    })
}

let checkedInfo;



shoppingCartCheckedOrderButton.addEventListener("click", () => {
    for (let i = 0; i< checkBtn.length; i ++){
        if (checkBtn[i].checked == true ){
            console.log(checkBtn[i])
            console.log(checkBtn[i].attributes.ea.value);
            console.log(checkBtn[i].value);
            console.log(productPrice[i])

            formDataProCode.value=checkBtn[i].value.trim();
            formDataProPrice.value=parseInt(productPrice[i].attributes.price.value);
            formDataProEa.value = checkBtn[i].attributes.ea.value.trim();

            $('input[name=productCode]').attr('value',formDataProCode.value=checkBtn[i].value.trim())
            $('input[name=price]').attr('value',parseInt(productPrice[i].attributes.price.value))
            $('input[name=productEa]').attr('value',checkBtn[i].attributes.ea.value.trim())


            console.log(formDataProCode.value.trim())
            console.log(formDataProPrice.value)
            console.log(formDataProEa.value.trim())
        }
    }
    cartForm.submit()
})