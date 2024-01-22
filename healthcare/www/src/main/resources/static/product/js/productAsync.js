let searchBtn = document.querySelector('#searchBtn'), // 검색버튼
    category = document.querySelector('#searchCategory'), // 카테고리
    keyword = document.querySelector('#searchKeyword'); // 키워드
    productList = document.querySelector('#productList'); // 상품목록

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
        let str = `<tr>
                        <th>상품번호</th>
                        <th>상품유형</th>
                        <th>상품명</th>
                        <th>상품가격</th>
                   </tr>`;
        for (let product of result) {
            str += `<tr
                        <td>${product.productNo}</td>
                        <td>${product.productType}</td>
                        <td>${product.productName}</td>
                        <td>${product.price}</td>
                    </tr>`;
            productList.innerHTML += str;
        } 
    });
}
