// 주문 취소 이벤트
// document.querySelectorAll(".order-remove-btn").addEventListener('click', e =>{
//     e
//     let orderNo = e.target.dataset.orderno, // 주문번호
//         orderPrice = e.target.dataset.orderprice, // 주문금액
//         orderPoint = e.target.dataset.point; // 적립금
//         console.log(orderNo, orderPrice, orderPoint);
//     if(confirm('주문을 취소하시겠습니까?')){
//         window.location.href = `/order/cancel/${orderNo}/${orderPoint}`;
//     }
// });

document.querySelectorAll(".order-remove-btn").forEach(e => {
    e.addEventListener("click", () =>{
        let orderNo = e.dataset.orderno, // 주문번호
            orderPrice = e.dataset.orderprice, // 주문금액
            orderPoint = e.dataset.point; // 적립금
            console.log(orderNo, orderPrice, orderPoint);
        if(confirm('주문을 취소하시겠습니까?')){
            window.location.href = `/order/cancel/${orderNo}/${orderPoint}`;
        }

    });
});
