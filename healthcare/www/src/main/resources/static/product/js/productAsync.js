let searchBtn = document.querySelector('#searchBtn'), // 검색버튼
    category = document.querySelector('#searchCategory'), // 카테고리
    keyword = document.querySelector('#searchKeyword'); // 키워드
    productList = document.querySelector('#productList'); // 상품목록 테이블

let openModal = document.querySelector('#searchModalOpen'), // 검색 모달창 오픈
    modalBack = document.querySelector('.search-modal-back'), // 모달 백그라운드
    modal = document.querySelector('.search-modal'), // 모달창
    closeBtn = document.querySelector('#searchModalClose'); // x버튼

// 검색 모달창 열기
openModal.addEventListener('click', () => {
    modalBack.classList.add('search-modal-back-on');
});
// 검색 모달창 닫기
closeBtn.addEventListener('click', () => {
    modalBack.classList.remove('search-modal-back-on');
    productList.innerHTML = '';
    keyword.value = null;
    category.value = '';
});

// 상품검색 이벤트 설치
searchBtn.addEventListener('click', () => {
    searchProduct();
});

// 서버에 상품검색 비동기 요청
async function searchProductFromServer(dataValue){
    try {
        const url = `/product/searchProduct?category=${dataValue.category}&keyword=${dataValue.keyword}`;
        const response = await fetch(url);
        const result = await response.json();
        console.log(result);
        return result;
    } catch (error) {
        console.log(error);        
    }
}

// 상품검색 함수
function searchProduct(){
    const dataValue = {
        category: category.value,
        keyword: keyword.value
    }
    console.log(dataValue);
    searchProductFromServer(dataValue).then(result =>{
        productList.innerHTML = '';
        let str = `<tr>
            <th>상품번호</th>
            <th>상품유형</th>
            <th>상품명</th>
            <th>상품가격</th>
            </tr>`;
        if(result != null){
            for (let product of result) {
                str += `<tr data-pno="${product.productNo}"
                data-ptype="${product.productType}"
                data-pname="${product.productName}"
                data-pinfo="${product.productInfo}"                                   
                data-price="${product.price}"
                data-regdate=${product.regDate}">
                <td class="pno">${product.productNo}</td>
                <td class="ptype">${product.productType}</td>
                <td class="pname">${product.productName}</td>
                <td class="price">${product.price}</td>
                </tr>`;
                productList.innerHTML = str;
            } 
        }
    });
}


let productType = document.querySelectorAll('#productType'), // 상품유형
    productNo = document.querySelector('#productNo'), // 상품번호
    productNoVal = document.querySelector('.product-no'); //상품번호 입력값
    productName = document.querySelectorAll('#productName'), // 상품명
    productInfo = document.querySelectorAll('#productInfo'), // 상품설명
    price = document.querySelectorAll('#price'), // 상품가격
    regDate = document.querySelector('#regDate'); // 상품등록일

productList.addEventListener('click', e => {
    let tr = e.target.closest('tr');
    if(tr.dataset.pno){
        console.log(tr);
        productNoVal.innerText = tr.dataset.pno;
        productNo.value = tr.dataset.pno;
        productName[1].value = tr.dataset.pname;
        productType[1].value = tr.dataset.ptype;
        productInfo[1].value = tr.dataset.pinfo;
        price[1].value = tr.dataset.price;
        regDate.value = tr.dataset.regdate;
        closeBtn.click();
    }
    
});

    
    


// 공백 포함 검사(공통)
function isSpace(value) {
    let regExp = /[\s]/; // 공백 체크 정규표현식
    return regExp.test(value);
}
// 상품등록 입력 검사
function validRegister(){
    if(isSpace(isSpace(price[0].value) || isSpace(productType[0].value))){
        alert('공백은 허용하지 않습니다.');
        return false;
    } else if(productName[0].value == '' || productType[0].value == '' || productInfo[0].value == '' || price[0].value == ''){
        alert('항목을 모두 입력해주세요.');
        return false;
    } else if(!/[0-9]/.test(price[0].value)){
        alert('상품가격은 숫자만 입력 가능합니다.')
        return false;
    }else{
        return true;
    }
}
// 상품수정 입력 검사
function validModify(){
    if(isSpace(isSpace(price[1].value) || isSpace(productType[1].value))){
        alert('공백은 허용하지 않습니다.');
        return false;
    } else if(productNo.value == '' || productName[1].value == '' || productType[1].value == '' || productInfo[1].value == '' || price[1].value == ''){
        alert('항목을 모두 입력해주세요.');
        return false;
    } else if(!/[0-9]/.test(price[1].value)){
        alert('상품가격은 숫자만 입력 가능합니다.')
        return false;
    }else{
        return true;
    }
}