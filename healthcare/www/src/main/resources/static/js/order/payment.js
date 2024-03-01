
const selectPg = document.querySelector('#selectPg'); // 결제 선택 select 태그 

// 유저 정보 받아오기
async function getUserInfoFromServer(){ 
    try {
        const response = await fetch('/order/userInfo');
        const result = await response.json();
        return result;
    } catch (error) {
        console.log(error);        
    }
}

// pg사 선택 select 값 가져오기
selectPg.addEventListener('change', e => {
    if(e.target.value == 'kakaopay'){
        pgVal = "kakaopay.TC0ONETIME";
        console.log(pgVal);
    } else if(e.target.value == 'tosspay'){
        pgVal = "tosspay.tosstest";
        console.log(pgVal);
    } else{
        pgVal = "";
    }
});

// 주문번호 생성
function generateOrderNumber(){
    const date = new Date();
    const year = date.getFullYear();
    const month = date.getMonth() + 1;
    const day = date.getDate();
    const hour = date.getHours();
    const minute = date.getMinutes();
    const second = date.getSeconds();
    const uuid = crypto.randomUUID();
    const order_no = year + month + day + hour + minute + second + '_' + uuid;
    return order_no;
}
// 주문명 생성
function getOrderName(){
    let productname = orderList[0].querySelector('.product-link').innerHTML;
    let otherQty = orderList.length - 1;
    return  productname + ' 외 ' + otherQty + '개 상품';
}

let pgVal; // pg사 종류 변수
let updateHeldPoint = 0; // 최종 사용 적립금
// 결제 요청 함수
function requestPay(){
    getUserInfoFromServer().then(u =>{
        if(pgVal == "" || pgVal == null){
            alert("결제방법을 선택해주세요.");
            return false;
        }
        const orderNumber = generateOrderNumber();

        const IMP = window.IMP;
        IMP.init('imp37747568');
        IMP.request_pay({
            pg: pgVal,
            pay_method: "card", // 생략가능
            merchant_uid: orderNumber, // 상점에서 생성한 고유 주문번호
            name: getOrderName(),
            amount: totalPriceCalc(), // 주문금액
            buyer_email: 'user@example.com', // 고객 이메일
            buyer_name: u.userName, // 고객명
            buyer_tel: u.userNumber, // 고객 연락처
            buyer_addr: u.userAddress, // 고객 주소
            buyer_postcode: "123-456", // 우편 번호
            // m_redirect_url: "/order/orderList" // 결제 완료 후 이동 페이지 설정
        }, function (rsp) { // callback 로직
            if (rsp.success) {
                console.log(rsp); // 주문성공
                success(u.userId, orderNumber).then(result => {
                    window.location.href = "/order/orderList";
                    console.log(result);
                });
            } else {
                console.log(rsp); // 주문실패
            }
        });
    });
}
// 주문 성공시 서버로 db반영 요청
async function success(userId, orderNumber) {
    try {
        const response = await fetch(`/order/save/${userId}/${orderNumber}/${updateHeldPoint}`);
        const result = await response.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

    
let orderList = document.querySelectorAll('.order-list'), // tr 리스트
totalPriceVal = document.querySelector('.total-price-value'), // 전체 상품금액
totalPointVal = document.querySelector('.total-point-value'); // 전체 상품 적립금

// 전체 상품 금액, 적립금 합산 함수
function getResultOrder(){
    let sumPrice = 0, sumPoint = 0;
    orderList.forEach(e => {
        sumPrice += parseInt(e.dataset.orderprice);
        sumPoint += parseInt(e.dataset.point);
    })
    console.log(sumPrice, sumPoint);
    totalPriceVal.innerHTML = numberFormatChange(sumPrice, '원');
    totalPointVal.innerHTML = numberFormatChange(sumPoint, '원');
    totalPriceVal.dataset.totalprice = sumPrice;
    totalPointVal.dataset.totalpoint = sumPoint;
}

// 장바구니 목록 수량 변경 이벤트(수량이 바뀌면 금액, 포인트값도 변경)
orderList.forEach(e => e.addEventListener('click', (e)=>{
    let orderNo = e.currentTarget.dataset.orderno, // 주문번호
        // productNo = e.currentTarget.dataset.productno, // 상품번호
        realPrice = e.currentTarget.dataset.realprice, // 상품가격
        accrualRate = e.currentTarget.dataset.accrualrate; // 적립률

    let qty, // 주문수량
        orderPrice, // 주문금액
        point; // 적립포인트
    if(e.target.id == 'orderMinus'){
        if(e.currentTarget.dataset.orderqty == 1){
            alert('최소 주문 수량은 1개 입니다.');
            return false;
        }
        e.currentTarget.dataset.orderqty--; // 주문수량 down
        qty = e.currentTarget.dataset.orderqty
        orderPrice = priceCalc(realPrice, qty); // 주문금액 계산
        point = pointCalc(orderPrice, accrualRate); // 적립포인트 계산

        e.currentTarget.dataset.orderprice = orderPrice;
        e.currentTarget.dataset.point = point;
        e.currentTarget.querySelector('.qty-value').innerHTML = qty; // 주문수량 갱신
        e.currentTarget.querySelector('.price-value').innerHTML = numberFormatChange(orderPrice, '원'); // 주문금액 갱신
        e.currentTarget.querySelector('.point-value').innerHTML = numberFormatChange(point, '원');  // 적립금 갱신
        heldPointVal.innerHTML = numberFormatChange(heldPoint, 'P'); // 보유 적립금 변경
        usePointVal.innerHTML = numberFormatChange(0, 'P'); // 사용 적립금 변경

        getResultOrder(); // 전체 금액 다신 계산
        updateCart(orderNo, orderPrice, qty, point); // 서버로 변경 사항 전달
    } else if(e.target.id == 'orderPlus'){
        e.currentTarget.dataset.orderqty++; // 주문수량 up
        qty = e.currentTarget.dataset.orderqty; 
        orderPrice = priceCalc(realPrice, qty); // 주문금액 계산
        point = pointCalc(orderPrice, accrualRate); // 적립포인트 계산

        e.currentTarget.dataset.orderprice = orderPrice;
        e.currentTarget.dataset.point = point;
        e.currentTarget.querySelector('.qty-value').innerHTML = qty; // 주문수량 갱신
        e.currentTarget.querySelector('.price-value').innerHTML = numberFormatChange(orderPrice, '원'); // 주문금액 갱신
        e.currentTarget.querySelector('.point-value').innerHTML = numberFormatChange(point, '원');  // 적립금 갱신
        heldPointVal.innerHTML = numberFormatChange(heldPoint, 'P'); // 보유 적립금 변경
        usePointVal.innerHTML = numberFormatChange(0, 'P'); // 사용 적립금 변경

        getResultOrder(); // 전체 금액 다신 계산
        updateCart(orderNo, orderPrice, qty, point); // 서버로 변경 사항 전달
    } else if(e.target.id == 'remove') { // 장바구니 제품 삭제
        if(confirm('상품을 삭제하시겠습니까?')){
            window.location.href = `/order/cart/delete/${orderNo}`; // 삭제요청
        }
    } 

}));

// 장바구니 비우기 확인
document.querySelector('#clearCart').addEventListener('click', () => {
    if(confirm('장바구니를 비우시겠습니까?')){
        window.location.href = '/order/cart/clear';
    }
});

let heldPointVal = document.querySelector('.held-point-value'), // 보유 적립금 표시
    usePointVal = document.querySelector('.use-point-value'); // 사용 적립금 표시
// 적립금 사용 이벤트
document.querySelector('#usePointBtn').addEventListener('click', () => {
    // heldPoint = thymeleaf에서 선언한 보유적립금 변수
    let usePointInput = document.querySelector('.use-point-input'); // 사용 적립금 입력 input
    let usePoint = parseInt(usePointInput.value); // 사용적립금
    if(isNaN(usePoint)){ // 숫자가 아닌 값을 입력하면..
        alert('숫자만 입력 가능합니다.');
        usePointInput.value = '';
        getResultOrder();
        return false;
    } else if(heldPoint < usePoint){ // 보유 적립금보다 사용적립금이 크면..
        usePointInput.value = '';
        getResultOrder();
        alert('보유적립금 한도내로 사용가능합니다.');
        return false;
    }
    // 적립금 사용 반영해서 다시 계산
    getResultOrder(); // 주문금액 리셋
    totalPriceVal.dataset.totalprice = totalPriceVal.dataset.totalprice - usePoint;
    let totalPrice = totalPriceVal.dataset.totalprice;
    totalPriceVal.innerHTML = numberFormatChange(totalPrice, '원'); // 주문 금액 변경
    heldPointVal.innerHTML = numberFormatChange(heldPoint - usePoint, 'P'); // 보유 적립금 변경
    usePointVal.innerHTML = numberFormatChange(usePoint, 'P'); // 사용 적립금 변경
    updateHeldPoint = usePoint;
    usePointInput.value = '';
});

// 숫자형식 지정 함수
function numberFormatChange(num, str){
    return new Intl.NumberFormat('en-US').format(num) + `${str}`;
}
// 결제금액 계산
function priceCalc(price, qty){
    return price * qty;
}
// 적립금 계산
function pointCalc(price, accrualRate){
    return Math.floor(price * accrualRate);
}
// 총 삼품금액 계산
function totalPriceCalc(){
    return parseInt(totalPriceVal.dataset.totalprice);
}

// 서버로 장바구니 수량, 금액, 포인트 변경 정보 전달
async function sendCartUpdateToServer(data){
    try {
        const response = await fetch('/order/cart/update', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });
        const result = await response.text();
        return result;
        
    } catch (error) {
        console.log(error);
    }
}
// 장바구니 변경 정보 맵핑해서 비동기함수로 전달
function updateCart(orderNoVal, priceVal, qtyVal, pointVal){
    let data = {
        orderNo: orderNoVal,
        orderPrice: priceVal,
        orderQty: qtyVal,
        point: pointVal
    }
    sendCartUpdateToServer(data).then(result => {
        if (result == 'success'){
            console.log('수량변경');
        }
    });
}
