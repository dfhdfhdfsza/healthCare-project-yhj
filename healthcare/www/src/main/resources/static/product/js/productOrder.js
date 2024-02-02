// thymeleaf에 productDTO객체를 product에 저장
let price = product.realPrice, // 판매가격
    orderResult = document.querySelector('#orderResult'), // 최종 금'
    minus = document.querySelector('#OrderMinus'), // 수량 down
    plus = document.querySelector('#OrderPlus'), // 수량 up
    qty = document.querySelector('.qty-value'), // 주문 수량
    qtyEvent = document.querySelector('.qty'), // 수량 조절 이벤트용 부모div
    credit = document.querySelector('#credit'); // 적립금
    discountRate = 0.01; // 적립율

    qtyEvent.addEventListener('click', e=>{
        qtyValue = parseInt(qty.innerHTML);
        console.log("가격 >> ",price);
        console.log("수량 >> ",qty.innerHTML);
        if(e.target.id == 'OrderMinus'){
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
        } else if(e.target.id == 'OrderPlus'){
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
            console.log(e.target.id);
            form.action = '/order/payment';
            form.method = 'POST';
            form.submit();
        }else if(e.target.id == 'cart'){
            form.action = '/order/cart';
            form.method = 'POST';
            console.log(e.target.id);
            form.submit();
        }
    });

    function checkHref(){
        
    }

    // // 서버로 장바구니 정보 전달
    // async function cartDataToServer(orderDTO){
    //     try {
    //         const url = '/order/cart';
    //         const config = {
    //             method: 'POST',
    //             headers: {
    //                 'Content-Type': 'application/json'
    //             },
    //             body: JSON.stringify(orderDTO)
    //         }
    //         let response = await fetch(url, config);
    //         let result = await response.text();
    //         return result;
    //     } catch (error) {
    //         console.log(error);
    //     }
    // }
    // // 서버로 주문 정보 전달
    // async function orderDataToServer(orderDTO){
    //     try {
    //         const url = '/order/payment';
    //         const config = {
    //             method: 'POST',
    //             headers: {
    //                 'Content-Type': 'application/json'
    //             },
    //             body: JSON.stringify(orderDTO)
    //         }
    //         let response = await fetch(url, config);
    //         let result = await response.text();
    //         return result;
    //     } catch (error) {
    //         console.log(error);
    //     }
    // }

    // function movePageChice(btn){
    //     console.log(btn);
    //     let productNoVal = product.productNo,
    //         productNameVal = product.productName,
    //         realPriceVal = product.realPrice,
    //         productInfoVal = product.productInfo,
    //         orderPriceVal = document.querySelector('#orderResult').dataset.resultprice,
    //         orderQtyVal = document.querySelector('#orderResult').dataset,
    //         pointVal = document.querySelector('.credits').dataset.point;
    //     const data = {
    //         productNo: productNoVal,
    //         productName: productNameVal,
    //         productInfo: productInfoVal,
    //         realPrice: realPriceVal,
    //         orderPrice: orderPriceVal,
    //         point: pointVal,
    //         orderQty: orderQtyVal
    //     }
    //     if(btn == 'cart'){
    //         cartDataToServer(data).then(result => {
    //             // alert('장바구니 추가 완료!');
    //             // location.href = '/order/cart';
    //         });    
    //     } else if(btn == 'buy'){
    //         orderDataToServer(data).then(result => {
    //             // alert('주문 완료!');
    //             // location.href = '/order/payment';
    //         });
    //     }
    // }

    // 결제금액 계산
    function priceCalc(price, qty){
        return price * qty;
    }
    // 적립금 계산
    function pointCalc(price, discountRate){
        return Math.floor(price * discountRate);
    }