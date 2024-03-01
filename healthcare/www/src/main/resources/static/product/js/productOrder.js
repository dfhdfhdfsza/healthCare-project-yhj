// thymeleaf에 productDTO객체를 product에 저장
let price = document.querySelector('.qty-value').dataset.price, // 판매가격
    orderResult = document.querySelector('#orderResult'), // 최종 금액
    minus = document.querySelector('#orderMinus'), // 수량 down
    plus = document.querySelector('#orderPlus'), // 수량 up
    qty = document.querySelector('.qty-value'), // 주문 수량
    qtyEvent = document.querySelector('.qty'), // 수량 조절 이벤트용 부모div
    credit = document.querySelector('#credit'); // 적립금
    discountRate = 0.01; // 적립율

    qtyEvent.addEventListener('click', e=>{
        qtyValue = parseInt(qty.innerHTML);
        console.log("가격 >> ",price);
        console.log("수량 >> ",qty.innerHTML);
        if(e.target.id == 'orderMinus'){
            if(qtyValue == 1){
                alert('최소 주문 수량은 1개 입니다.');
                return false;
            }else{
                let getQty = qtyValue - 1;
                let resultPrice = priceCalc(price, getQty);
                let creditVal = pointCalc(resultPrice, discountRate);
                qty.innerHTML = getQty;
                orderResult.innerHTML = new Intl.NumberFormat('en-US').format(resultPrice) + '원';
                document.querySelector('#point').value = creditVal;
                document.querySelector('#orderQty').value = getQty;
                document.querySelector('#orderPrice').value = resultPrice;
                orderResult.dataset.price = resultPrice;
                qty.dataset.qty = getQty;
                credit.dataset.point = creditVal;
                credit.innerHTML = new Intl.NumberFormat('en-US').format(creditVal) + '원';
            }
        } else if(e.target.id == 'orderPlus'){
            let getQty = qtyValue + 1;
            let resultPrice = priceCalc(price, getQty);
            let creditVal = pointCalc(resultPrice, discountRate);
            qty.innerHTML = getQty;
            orderResult.innerHTML = new Intl.NumberFormat('en-US').format(resultPrice) + '원';
            document.querySelector('#point').value = creditVal;
            document.querySelector('#orderQty').value = getQty;
            document.querySelector('#orderPrice').value = resultPrice;
            orderResult.dataset.price = resultPrice;
            qty.dataset.qty = getQty;
            credit.dataset.point = creditVal;
            credit.innerHTML = new Intl.NumberFormat('en-US').format(creditVal) + '원';
        }
    });
    

    let button = document.querySelector('.order-button'),
        form = document.querySelector('.product-detail-form');
    button.addEventListener('click', e => {
        if(e.target.id == 'buy'){
            form.action = '/order/cart';
            form.method = 'POST';
            form.submit();
        }else if(e.target.id == 'cart'){
            if(confirm('장바구니에 상품을 추가하였습니다.\n장바구니를 확인하시겠습니까?')){
                form.action = '/order/cart';
                form.method = 'POST';
                form.submit();
            }else{
                
            }
        }
    });


    // 결제금액 계산
    function priceCalc(price, qty){
        return price * qty;
    }
    // 적립금 계산
    function pointCalc(price, discountRate){
        return Math.floor(price * discountRate);
    }