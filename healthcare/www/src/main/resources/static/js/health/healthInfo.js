$(document).ready(function () {
    $('.slide-wrapper').slick({
        infinite: true, //무한반복 옵션
        slidesToShow: 10,    //한 화면에 보여질 컨텐츠 개수
        slidesToScroll: 5,   //스크롤 한번에 움직일 컨텐츠 개수
        dots: false, // 좌우 arrow 네비게이션 안보이게 하기
        arrows: false, // 아래 dost 네비게이션 안보이게 하기
    });
});


//운동리스트 불러오기
function getExerciseInfo(equipment, bodypart, page, size) {
    $.ajax({
        async: true,
        type: 'get',
        url: `/health/getExerciseInfo?bodypart=${bodypart}&equipment=${equipment}&page=${page}&size=${size}`,
        success: function (res) {
            // console.log(res);
            spreadExerciseInfo(res.content);
            spreadPaging(res.pageable, res.totalPages);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) { // 비동기 통신이 실패할경우 error 콜백으로 들어옵니다.
            alert("통신 실패.");
        }
    });
}


//운동리스트받아와서 화면에 뿌려주는 함수
function spreadExerciseInfo(list) {
    let listDiv = document.querySelector('.list-div');
    listDiv.innerHTML = '';

    for (let i = 0; i < list.length; i++) {
        let str = `<div class="exercise-item ${list[i].name}" data-name="${list[i].name}">`;
        str += `<div class="exercise-image"><image src="${list[i].imgUrl}" class="eximg"/></div>`
        str += `<div class="exercise-detail">`
        str += `<div class="exercise-name">${list[i].name}</div>`
        str += `<div class="exercise-name">bodypart : ${list[i].bodypart}</div>`
        str += `<div>secondary muscles : ${list[i].secondaryMuscles}</div>`
        str += `<div>equipment : ${list[i].equipment}</div>`
        str += `</div></div>`

        listDiv.innerHTML += str;
    }

}

let targetSelectBtns = document.querySelectorAll('.target-select');
let equipmentSelectBtns = document.querySelectorAll('.equipment-select');

//운동부위 선택해서 불러오기
targetSelectBtns.forEach(function (targetSelectBtn) {
    targetSelectBtn.addEventListener('click', function () {
        let target = targetSelectBtn.getAttribute('data-target');

        //모든 장비버튼 검은색으로 초기화
        equipmentSelectBtns.forEach(function (equipmentSelectBtn) {
            equipmentSelectBtn.style.color = 'black';
        })
        //모든 부위버튼 검은색으로 초기화
        targetSelectBtns.forEach(function (targetSelectBtn) {
            targetSelectBtn.style.color = 'black';
        })

        //누른 부위버튼 빨간색으로 변경
        targetSelectBtn.style.color = 'red';

        getExerciseInfo("", target, 0, 10);

    })
})

//운동장비 선택해서 불러오기
equipmentSelectBtns.forEach(function (equipmentSelectBtn) {
    equipmentSelectBtn.addEventListener('click', function () {
        let equipment = equipmentSelectBtn.getAttribute('data-equipment');

        //모든 장비버튼 검은색으로 초기화
        equipmentSelectBtns.forEach(function (equipmentSelectBtn) {
            equipmentSelectBtn.style.color = 'black';
        })
        //모든 부위버튼 검은색으로 초기화
        targetSelectBtns.forEach(function (targetSelectBtn) {
            targetSelectBtn.style.color = 'black';
        })

        //누른 장비버튼 빨간색으로 변경
        equipmentSelectBtn.style.color = 'red';

        getExerciseInfo(equipment, "", 0, 10);

    })
})


//페이징버튼 관련 function
function spreadPaging(pageable, totalPages) {
    const qty = 10;
    let targetSelectBtn = document.querySelector('.bodyPart-div .target-select[style="color: red;"]');  //선택된 부위의 button
    let equipmentSelectBtn = document.querySelector('.equipment-div .equipment-select[style="color: red;"]');  //선택된 장비의 button
    let equipment, target;

    if (targetSelectBtn) {
        target = targetSelectBtn.getAttribute('data-target');
        equipment = "";
    } else if (equipmentSelectBtn) {
        equipment = equipmentSelectBtn.getAttribute('data-equipment');
        target = "";
    }


    let pagingDiv = document.querySelector('.paging-div');
    pagingDiv.innerHTML = '';

    //시작페이지 엔드페이지 계산
    let pageEnd = Math.ceil((pageable.pageNumber + 1) / qty) * qty;
    let pageStart = pageEnd - qty;
    pageEnd = Math.min(pageEnd, totalPages);
    console.log("시작:" + pageStart + " 끝:" + pageEnd + " 현재:" + (pageable.pageNumber));

    if (pageStart > 1) {
        pagingDiv.innerHTML += `<button type="button" class="prevPageBtn"><i class="fa-solid fa-caret-left"></i></button>`
    }

    for (let i = pageStart; i < pageEnd; i++) {
        let str = `<span class="pageNo-span" data-pageNo=${i}>${i + 1}</span>`;
        pagingDiv.innerHTML += str;
    }

    if (pageEnd < totalPages) {
        pagingDiv.innerHTML += `<button type="button" class="nextPageBtn"><i class="fa-solid fa-caret-right"></i></i></button>`
    }
    //paging span을 눌렀을때의 이벤트
    let pageNoSpans = document.querySelectorAll('.pageNo-span');
    pageNoSpans.forEach(function (pageNoSpan) {
        pageNoSpan.addEventListener('click', function () {
            let pageNo = pageNoSpan.getAttribute('data-pageNo');
            getExerciseInfo(equipment, target, pageNo, 10);
            selectSpan(pageNo);
        })
    })

    //이전 버튼을 눌렀을때의 이벤트
    let prevPageBtn = document.querySelector('.prevPageBtn');
    if (prevPageBtn) {
        prevPageBtn.addEventListener('click', () => {
            getExerciseInfo(equipment, target, pageStart - 1, 10);
            selectSpan(pageStart - 1);
        });
    }

    //다음 버튼을 눌렀을때의 이벤트
    let nextPageBtn = document.querySelector('.nextPageBtn');
    if (nextPageBtn) {
        nextPageBtn.addEventListener('click', () => {
            getExerciseInfo(equipment, target, pageEnd + 1, 10);
            selectSpan(pageEnd + 1);
        });
    }

}
// let selectedSpan = document.querySelector([data-pageNo="${pageNo}"]);

function selectSpan(pageNo) {
    // console.log(pageNo);
    let pageNoSpans = document.querySelectorAll('.pageNo-span');
    let selectedSpan = document.querySelector(`[data-pageNo="${pageNo}"]`);
    // console.log(selectedSpan);

    pageNoSpans.forEach(function (pageNoSpan) {
        pageNoSpan.style.color = 'black';
    });

    selectedSpan.style.color = 'red';
}




document.addEventListener('click', (e) => {

    if (e.target.classList.contains('exercise-item')) {   //운동div를 눌렀을때
        modal.style.display = "flex";
    }
    else if (e.target.parentNode.classList.contains('exercise-item')) {//운동div의 하위div를 눌렀을때
        modal.style.display = "flex";
    }
    else if (e.target.parentNode.parentNode.classList.contains('exercise-item')) {//운동div의 하위div의 하위div를 눌렀을때
        modal.style.display = "flex";
    }
})

//모달창 값 가져오는 function

//모달창에 값 뿌려주는 function

//모달창 닫기
let closeBtns = modal.querySelectorAll(".close-area")
closeBtns.forEach(function (closeBtn) {
    closeBtn.addEventListener("click", (e) => {
        modal.style.display = "none"
    })
});

//모달창 바깥부분 눌렀을때 닫기
modal.addEventListener("click", (e) => {
    if (e.target.classList.contains("modal-overlay")) {
        modal.style.display = "none"
    }
})

