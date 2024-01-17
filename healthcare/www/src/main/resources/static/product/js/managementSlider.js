// 메인 슬라이더

function slider(wrapperName){ // 슬라이드 모듈화 함수
    // 변수 설정
    let slide = document.querySelector('.' + wrapperName); // slider wraper
    let items = document.querySelectorAll('.items'); // 슬라이드 아이템 리스트
    let index = 1; // 슬라이드 인덱스
    let slideQty = items.length; // 슬라이드 개수
    let itemWidth = slide.clientWidth// 슬라이드 아이템 width
    let margin = itemWidth; // 마진 필요시 입력
    let startPosition = itemWidth; // 초기 위치 설정값 변수
    let regBtn = document.querySelector('.select-register-menu'); // 상품등록버튼
    let modBtn = document.querySelector('.select-modify-menu'); // 상품수정버튼
    let wrapper = document.querySelector('.management-wrapper'); // wrapper
    let selectMenu = document.querySelector('.select-management-close'); // 메뉴선택
    let container = document.querySelector('.product-management-container') // 전체 배경

    updateWidth();
    initTransform();

    // slide wrapper widh 설정 함수
    function updateWidth(){
        let slideTotalQty = document.querySelectorAll('.items').length; // 총 슬라이드 개수(복사본포함)
        console.log('아이템 개수 >>'+slideTotalQty);
        console.log('아이템 width >>'+itemWidth);
        // slider.style.width = `${itemsQty * 설정할값}설정단위`;
        slide.style.width = `${slideTotalQty * (itemWidth + (margin/2))}px`; // 전체 슬라이더 width
    }

    // 초기 슬라이드위치 설정 함수
    function initTransform(){
        // 복사본(초기 슬라이드 개수) * 슬라이드width 만큼 이동
        slide.style.transform = `translateX(-${startPosition}px)`;
    }

    // 트렌지션 효과 함수
    function addTransition(time){
        slide.style.transition = `${time}s`;
    }
    // form 오픈 이벤트

    // 상품등록
    regBtn.addEventListener('click', ()=>{
        if(index > 0)
        slide.style.visibility = 'visible';
        addTransition(0.7);
        slide.style.transform = `translateX(0px)`;
        index = 0;
        selectMenu.classList.add('select-management-toggle');
        modBtn.classList.add('menu-button-toggle');
        regBtn.classList.add('menu-button-toggle');
        wrapper.classList.add('management-wrapper-toggle');
        container.classList.add('product-management-toggle');
    });
    // 상품수정
    modBtn.addEventListener('click', ()=>{
        if(index < 2)
        slide.style.visibility = 'visible';
        addTransition(0.7);
        slide.style.transform = `translateX(-${itemWidth+margin}px)`;
        index = 2;
        selectMenu.classList.add('select-management-toggle');
        modBtn.classList.add('menu-button-toggle');
        regBtn.classList.add('menu-button-toggle');
        wrapper.classList.add('management-wrapper-toggle');
        container.classList.add('product-management-toggle');
    });


}