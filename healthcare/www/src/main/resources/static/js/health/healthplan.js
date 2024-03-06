//슬라이드 라이브러리
$(document).ready(function () {
    $('.slide-wrapper').slick({
        infinite: true, //무한반복 옵션
        slidesToShow: 6,    //한 화면에 보여질 컨텐츠 개수
        slidesToScroll: 2,   //스크롤 한번에 움직일 컨텐츠 개수
        dots: false, // 좌우 arrow 네비게이션 안보이게 하기
        arrows: false, // 아래 dost 네비게이션 안보이게 하기
    });
});

let speradPlanList;

//계획리스트 불러오기
function getEventList() {
    $.ajax({
        type: "GET",            // HTTP method type(GET, POST) 형식이다.
        url: "/health/getEventList?userNo=1",      // 컨트롤러에서 대기중인 URL 주소이다.  로그인한 id(수정할예정)
        async: true,
        success: function (res) { // 비동기통신의 성공일경우 success콜백으로 들어옵니다. 'res'는 응답받은 데이터이다.
            speradPlanList = new Array();
            for (let i = 0; i < res.length; i++) {
                // events: [    이런형태의 json을 만들어준다
                //     {
                //         title: 'BCH237',
                //         start: '2024-01-12',
                //         extendedProps: {
                //             department: 'BioChemistry'
                //         },
                //         description: 'Lecture'
                //     }
                // ]
                let plan = new Object();
                //표준속성
                plan.title = res[i].title;
                plan.start = res[i].start;

                //비표준속성객체
                let extendedProps = new Object();

                //userPlan
                let userPlanJSON = new Object();
                userPlanJSON.userPlanNo = res[i].userPlan.userPlanNo;
                userPlanJSON.planDate = res[i].userPlan.planDate;
                userPlanJSON.userNo = res[i].userPlan.userNo;
                userPlanJSON.planNo = res[i].userPlan.planNo;

                //planCalendar
                let planCalendarJSON = new Object();
                planCalendarJSON.planNo = res[i].planCalendar.planNo;
                planCalendarJSON.exerciseName = res[i].planCalendar.exerciseName;

                //exerciseSetList
                let exerciseSetListJSONARRAY = new Array();
                for (let j = 0; j < res[i].exerciseSetList.length; j++) {
                    let exerciseSetListJSON = new Object();
                    exerciseSetListJSON.exerciseSetNo = res[i].exerciseSetList[j].exerciseSetNo;
                    exerciseSetListJSON.planNo = res[i].exerciseSetList[j].planNo;
                    exerciseSetListJSON.exerciseWeight = res[i].exerciseSetList[j].exerciseWeight;
                    exerciseSetListJSON.exerciseCount = res[i].exerciseSetList[j].exerciseCount;
                    exerciseSetListJSON.exerciseCheck = res[i].exerciseSetList[j].exerciseCheck;
                    exerciseSetListJSONARRAY.push(exerciseSetListJSON);
                }

                //만든 JSON객체들을 비표준속성 객체에 추가
                extendedProps.userPlan = userPlanJSON;
                extendedProps.planCalendar = planCalendarJSON;
                extendedProps.exerciseSetList = exerciseSetListJSONARRAY;

                plan.extendedProps = extendedProps;

                speradPlanList.push(plan);

            }
            calendar.setOption('events', speradPlanList);
            calendar.render();

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) { // 비동기 통신이 실패할경우 error 콜백으로 들어옵니다.
            alert("통신 실패.");
        }
    });

}

// calendar element 취득
let calendarEl = $('#calendar')[0];
let selectDate;

// full-calendar 생성하기
let calendar = new FullCalendar.Calendar(calendarEl, {

    height: '1000px', //calendar 높이 설정
    aspectRatio: 0.6,
    expandRows: true, //화면에 맞게 높이 재설정
    slotMinTime: '08:00', // Day 캘린더에서 시작 시간
    slotMaxTime: '20:00', // Day 캘린더에서 종료 시간
    //헤더에 표시할 툴바
    headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
    },
    initialView: 'dayGridMonth', // 초기 로드 될때 보이는 캘린더 화면(기본 설정: 달)
    //initialDate: '2021-07-15', // 초기 날짜 설정 (설정하지 않으면 오늘 날짜가 보인다.)
    // navLinks: true, // 날짜를 선택하면 Day 캘린더나 Week 캘린더로 링크
    editable: true, // 수정 가능?
    // selectable: true, // 달력 일자 드래그 설정가능
    // nowIndicator: true, // 현재 시간 마크
    // dayMaxEvents: true, // 이벤트가 오버되면 높이 제한 (+ 몇 개식으로 표현)
    locale: 'ko', // 한국어 설정

    // eventAdd: function (obj) { // 이벤트가 추가되면 발생하는 이벤트
    //     console.log(obj);
    // },
    // eventChange: function (obj) { // 이벤트가 수정되면 발생하는 이벤트
    //     console.log(obj);
    // },
    // eventRemove: function (obj) { // 이벤트가 삭제되면 발생하는 이벤트
    //     console.log(obj);
    //     getEventList();
    // },
    eventDrop: function (info) {    //드래그로 이벤트 수정하기
        
        let userPlanObj=new Object(info.oldEvent._def.extendedProps.userPlan); //드래그한 플랜객체
        let newdate = new Date(info.event._instance.range.start);   //드래그한 새로운날짜

        // YYYY-MM-DD 형식으로 만들어주기
        let year = newdate.getFullYear();
        let month = ('0' + (newdate.getMonth() + 1)).slice(-2); // 월은 0부터 시작하므로 1을 더하고, 두 자리로 표현
        let day = ('0' + newdate.getDate()).slice(-2); // 일자를 두 자리로 표현
        let formattednewdate = year + '-' + month + '-' + day;

        userPlanObj.planDate=formattednewdate;
        
        //비동기로 유저플랜객체의 날짜 수정
        $.ajax({
            async: true,
            type: 'post',
            url: '/health/modPlanDate',
            data: JSON.stringify(userPlanObj),
            contentType: 'application/json',
            success: function () {
                alert('날짜변경성공');
                getEventList();
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) { // 비동기 통신이 실패할경우 error 콜백으로 들어옵니다.
                alert("통신 실패.");
            }
        });
    },
    dateClick: function (info) {
        //모달창 띄우기
        let modal = document.getElementById("modal");
        modal.style.display = "flex";
        let planlist = document.querySelector('.planlist');
        planlist.style.display = "flex";
        //모달창에 날짜뿌려주기
        let title = document.querySelector("#modal .title h2");
        title.innerHTML = info.dateStr;
        selectDate = info.dateStr;

        //모달창에 계획리스트 뿌려주기
        let clickedEventlist = new Array();

        for (let i = 0; i < speradPlanList.length; i++) {
            if (speradPlanList[i].start == info.dateStr) {
                clickedEventlist.push(speradPlanList[i]);
            }
        }
        spreadPlanListContent(clickedEventlist);
    },
    // select: function(arg) { // 캘린더에서 드래그로 이벤트를 생성할 수 있다.
    //     var title = prompt('Event Title:');
    //     if (title) {            
    //         calendar.addEvent({
    //             title: title,
    //             start: arg.start,
    //             end: arg.end,
    //             allDay: arg.allDay
    //         })          
    //     }          
    //     calendar.unselect()
    // }

    events: getEventList()
});
calendar.render();

let exerciseSelectDiv = document.querySelector('.exercise-select'); //운동선택div
let planlistDiv = document.querySelector('.planlist');  //플랜 리스트 div
let setsettingdiv = document.querySelector('.set-setting'); //세트설정div
let setDetailDiv = document.querySelector('.set-detail');//플랜 디테일 div

let nextPageExerciseListBtn = document.getElementById('next-page'); //운동리스트 다음페이지버튼
let previousPageExerciseListBtn = document.getElementById('previous-page'); //운동리스트 이전페이지버튼

//계획리스트 뿌려주는 function
function spreadPlanListContent(clickedEventlist) {
    let content = document.querySelector('.content');
    content.innerHTML = ``;
    if (clickedEventlist.length != 0) {
        for (let i = 0; i < clickedEventlist.length; i++) {
            let extendedProps = JSON.stringify(clickedEventlist[i].extendedProps);
            content.innerHTML += `<div>
            <span>${clickedEventlist[i].title}</span>
            <button type="button" class="setDetail" data-ep='${extendedProps}'>자세히 보기</button>
            <button type="button" class="planModify" data-ep='${extendedProps}'>수정</button>
            <button type="button" class="planDelete" 
                data-upn='${clickedEventlist[i].extendedProps.userPlan.userPlanNo}'>삭제</button>
            </div>`;
        }
    }
    else {
        content.innerHTML += `<div>
        계획이 없습니다.
        </div>`;
    }

    //플랜 자세히보기
    let setDetailBtns = document.querySelectorAll('.setDetail');
    setDetailBtns.forEach(function (setDetailBtn) {
        setDetailBtn.addEventListener('click', (e) => {
            let setDetailDiv = document.querySelector('.set-detail');
            setDetailDiv.style.display = 'flex';
            planlistDiv.style.display = "none";

            //json으로 파싱해서 function에 매개변수로 전달
            let extendedProps = JSON.parse(setDetailBtn.dataset.ep);
            spreadSetDetail(extendedProps);
            //수정button 숨기기
            let modifyBtn = document.getElementById('mod-complete');
            modifyBtn.style.display = 'none';
            let exerciseModBtn = document.querySelector('.exerciseMod');
            exerciseModBtn.style.display = 'none';
        });
    });

    //플랜 수정div 열기
    let planModifyBtns = document.querySelectorAll('.planModify');
    planModifyBtns.forEach(function (planModifyBtn) {
        planModifyBtn.addEventListener('click', (e) => {
            let setDetailDiv = document.querySelector('.set-detail');
            setDetailDiv.style.display = 'flex';
            planlistDiv.style.display = "none";

            //json으로 파싱해서 function에 매개변수로 전달
            let extendedProps = JSON.parse(planModifyBtn.dataset.ep);
            spreadSetDetail(extendedProps);

            //수정버튼 보여주기
            let modifyBtn = document.getElementById('mod-complete');
            modifyBtn.style.display = 'flex';
            let exerciseModBtn = document.querySelector('.exerciseMod');
            exerciseModBtn.style.display = 'flex';

            //중량,개수 수정가능하게 바꾸기
            let setDetailWeights = document.querySelectorAll('.setDetailWeight');
            setDetailWeights.forEach(function (setDetailWeight) {
                setDetailWeight.readOnly = false;
            })

            let setDetailCounts = document.querySelectorAll('.setDetailCount');
            setDetailCounts.forEach(function (setDetailCount) {
                setDetailCount.readOnly = false;
            })
        });
    });

}

let modifyBtn = document.getElementById('mod-complete');
modifyBtn.addEventListener('click', modifyPlan);


//플랜 수정
function modifyPlan() {

    let planCalendar = new Object();
    let planNo = document.querySelector('.planNo').value;
    planCalendar.planNo = planNo;
    let exerciseName = document.querySelector('.exerciseName').value;
    planCalendar.exerciseName = exerciseName;


    // tbody 요소 선택
    let setDetailList = document.querySelector('.set-detail .set-table tbody');

    // tbody 내의 모든 tr 요소 선택
    let rows = setDetailList.querySelectorAll('tr');

    let exerciseSetJSONARRAY = new Array();


    // 각 <tr> 요소를 순회하며 데이터 추출
    rows.forEach(row => {
        let setNo = row.getAttribute('data-setNo');
        let rowPlanNo = row.getAttribute('data-planNo');
        let weight = row.querySelector('.setDetailWeight').value;
        let count = row.querySelector('.setDetailCount').value;
        let isChecked = row.querySelector('[type="checkbox"]').checked;

        // 추출한 데이터를 객체로 추가
        let exerciseSetJSON = new Object();
        exerciseSetJSON.exerciseSetNo = setNo;
        exerciseSetJSON.planNo = rowPlanNo;
        exerciseSetJSON.exerciseWeight = weight;
        exerciseSetJSON.exerciseCount = count;
        exerciseSetJSON.exerciseCheck = isChecked;
        exerciseSetJSONARRAY.push(exerciseSetJSON);
    });


    let modExtendedProps = {

        planCalendar: planCalendar,
        exerciseSetList: exerciseSetJSONARRAY
    };

    console.log(modExtendedProps);

    $.ajax({
        async: true,
        type: 'post',
        url: '/health/modPlan',
        data: JSON.stringify(modExtendedProps),
        contentType: 'application/json',
        // processData: false,
        success: function (modExtendedProps) {
            alert('수정성공');
            getEventList();
            closeModal();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) { // 비동기 통신이 실패할경우 error 콜백으로 들어옵니다.
            alert("통신 실패.");
        }
    });
}

//플랜 삭제
let planDeleteBtns = document.querySelectorAll('.planDelete');
planDeleteBtns.forEach(function (planDeleteBtn) {
    planDeleteBtn.addEventListener('click', (e) => {
        let userPlanNo = planDeleteBtn.dataset.upn;
        DeletePlan(userPlanNo);
    });
});

//모달창 닫기
let closeBtns = modal.querySelectorAll(".close-area")
closeBtns.forEach(function (closeBtn) {
    closeBtn.addEventListener("click", (e) => {
        closeModal();
    })
});

//set detail div 닫기
let closeSetdetailBtn = document.querySelector('.close-setdetail');
closeSetdetailBtn.addEventListener('click', (e) => {
    let setDetailDiv = document.querySelector('.set-detail');
    setDetailDiv.style.display = 'none';
})

//모달창 바깥부분 눌렀을때 닫기
modal.addEventListener("click", (e) => {

    if (e.target.classList.contains("modal-overlay")) {
        closeModal();
    }
})

//모달창 닫는 function
function closeModal() {
    modal.style.display = "none"
    exerciseSelectDiv.style.display = "none";
    setsettingdiv.style.display = "none";

    //모든 부위버튼 흰색으로 초기화
    let targetSelectBtns = document.querySelectorAll('.target-select');
    targetSelectBtns.forEach(function (targetSelectBtn) {
        targetSelectBtn.style.color = 'white';
    })

    //모든 장비버튼 흰색으로 초기화
    let equipmentSelectBtns = document.querySelectorAll('.equipment-select');
    equipmentSelectBtns.forEach(function (equipmentSelectBtn) {
        equipmentSelectBtn.style.color = 'white';
    })

    //페이징 div 초기화
    let pagingDiv = document.querySelector('.paging-div');
    pagingDiv.innerHTML = '';



    //운동 list 지우기
    let exerciseListDiv = document.querySelector('.exercise-list');
    exerciseListDiv.innerHTML = '';
    //input값 지우기
    document.getElementById('target').value = '';
    document.getElementById('offset').value = '';
    document.getElementById('exercise-name-input').value = '';

    nextPageExerciseListBtn.style.display = 'none';
    previousPageExerciseListBtn.style.display = 'none';

    //세트 초기화
    let setDiv = document.querySelector('.set-setting .set-table tbody');
    setDiv.innerHTML = `<tr class="set-list">
    <td>1</td>
    <td><input type="text" name="Weight"> kg</td>
    <td><input type="text" name="count"></td>
    <td><input type="checkbox" name="check"></td>
    </tr>`;

    //set detail창 닫기
    let setDetailDiv = document.querySelector('.set-detail');
    setDetailDiv.style.display = 'none';

    //수정button 숨기기
    let modifyBtn = document.getElementById('mod-complete');
    modifyBtn.style.display = 'none';

}

//모달창 운동추가 버튼눌렀을때 div변경
let exaddBtn = document.querySelector(".exercise-add");
exaddBtn.addEventListener('click', (e) => {
    exerciseSelectDiv.style.display = 'flex'
    $('.slide-wrapper').slick('setPosition');
    planlistDiv.style.display = "none";

    let addFooterDiv = document.querySelector('.add-footer');
    addFooterDiv.style.display = "flex";

    let modFooterDiv = document.querySelector('.mod-footer');
    modFooterDiv.style.display = "none";
})

//플랜 디테일창에서 운동교체 버튼눌렀을때 div표시
let exerciseModBtn = document.querySelector('.exerciseMod');
exerciseModBtn.addEventListener('click', (e) => {
    exerciseSelectDiv.style.display = 'flex'
    $('.slide-wrapper').slick('setPosition');

    let addFooterDiv = document.querySelector('.add-footer');
    addFooterDiv.style.display = "none";

    let modFooterDiv = document.querySelector('.mod-footer');
    modFooterDiv.style.display = "flex";
})

//세팅설정 버튼을 눌렀을때
let setBtn = document.querySelector('.setBtn');
setBtn.addEventListener('click', (e) => {
    let exerciseNameInpunt = document.getElementById('exercise-name-input');
    if (exerciseNameInpunt.value != '') {
        setsettingdiv.style.display = "flex";
        exerciseSelectDiv.style.display = "none";
    }
    else {
        alert('운동을 선택해주세요!');
    }
})

//운동 선택 창에서 교체 버튼눌렀을때
let modBtn = document.querySelector('.modBtn');
modBtn.addEventListener('click', (e) => {
    exerciseSelectDiv.style.display = "none";
})


//뒤로가기 버튼
let backBtns = document.querySelectorAll('.backBtn');
backBtns.forEach(function (backBtn) {

    backBtn.addEventListener('click', (e) => {

        if (exerciseSelectDiv.style.display == "flex") {    //현재 modal창이 exercise-select div일때
            exerciseSelectDiv.style.display = "none"
            planlistDiv.style.display = "flex";
        }
        else if (setsettingdiv.style.display == "flex") {   //현재 modal창이 set-setting div일때
            exerciseSelectDiv.style.display = "flex";
            setsettingdiv.style.display = "none";
        }
        else if (setDetailDiv.style.display == "flex") {
            planlistDiv.style.display = "flex";
            setDetailDiv.style.display = "none";
        }
    })
})

let targetSelectBtns = document.querySelectorAll('.target-select');
let equipmentSelectBtns = document.querySelectorAll('.equipment-select');

//운동부위 선택해서 불러오기
targetSelectBtns.forEach(function (targetSelectBtn) {
    targetSelectBtn.addEventListener('click', function () {
        let target = targetSelectBtn.getAttribute('data-target');


        //모든 부위버튼 배경색 검은색으로 초기화
        targetSelectBtns.forEach(function (targetSelectBtn) {
            targetSelectBtn.style.color = 'white';
        })
        //모든 장비버튼 흰색으로 초기화
        equipmentSelectBtns.forEach(function (equipmentSelectBtn) {
            equipmentSelectBtn.style.color = 'white';
        })

        //누른 부위버튼 배경색 빨간색으로 변경
        targetSelectBtn.style.color = 'red';

        getExerciseInfo("", target, 0, 5);

    })
})


equipmentSelectBtns.forEach(function (equipmentSelectBtn) {
    equipmentSelectBtn.addEventListener('click', function () {
        let equipment = equipmentSelectBtn.getAttribute('data-equipment');

        //모든 장비버튼 흰색으로 초기화
        equipmentSelectBtns.forEach(function (equipmentSelectBtn) {
            equipmentSelectBtn.style.color = 'white';
        })
        //모든 부위버튼 흰색으로 초기화
        targetSelectBtns.forEach(function (targetSelectBtn) {
            targetSelectBtn.style.color = 'white';
        })

        //누른 장비버튼 빨간색으로 변경
        equipmentSelectBtn.style.color = 'red';

        getExerciseInfo(equipment, "", 0, 5);

    })
})

function getExerciseInfo(equipment, bodypart, page, size) {
    $.ajax({
        async: true,
        type: 'get',
        url: `/health/getExerciseInfo?bodypart=${bodypart}&equipment=${equipment}&page=${page}&size=${size}`,
        success: function (res) {
            // console.log(res);
            spreadExerciseList(res.content);
            spreadPaging(res.pageable, res.totalPages);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) { // 비동기 통신이 실패할경우 error 콜백으로 들어옵니다.
            alert("통신 실패.");
        }
    });
}


// 운동리스트 div에 뿌려주는 function
function spreadExerciseList(list) {
    let exerciseListDiv = document.querySelector('.exercise-list');
    exerciseListDiv.innerHTML = '';
    for (let i = 0; i < list.length; i++) {
        let str = `<div class="exercise-item ${list[i].name}" data-name="${list[i].name}">`;
        str += `<div class="exercise-image"><image src="${list[i].gifUrl}" class="eximg"/></div>`
        str += `<div class="exercise-detail">`
        str += `<div class="exercise-name">${list[i].name}</div>`
        str += `<div>${list[i].secondaryMuscles}</div>`
        str += `</div></div>`

        exerciseListDiv.innerHTML += str;
    }
}



//페이징
function spreadPaging(pageable, totalPages) {
    const qty = 5;
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
            getExerciseInfo(equipment, target, pageNo, qty);
            
        })
    })

    //이전 버튼을 눌렀을때의 이벤트
    let prevPageBtn = document.querySelector('.prevPageBtn');
    if (prevPageBtn) {
        prevPageBtn.addEventListener('click', () => {
            getExerciseInfo(equipment, target, pageStart - 1, qty);
            
        });
    }

    //다음 버튼을 눌렀을때의 이벤트
    let nextPageBtn = document.querySelector('.nextPageBtn');
    if (nextPageBtn) {
        nextPageBtn.addEventListener('click', () => {
            getExerciseInfo(equipment, target, pageEnd, qty);
            
        });
    }
    selectSpan(pageable.pageNumber);

}


function selectSpan(pageNo) {
    console.log(pageNo);
    let pageNoSpans = document.querySelectorAll('.pageNo-span');
    let selectedSpan = document.querySelector(`[data-pageNo="${pageNo}"]`);
    console.log(selectedSpan);

    pageNoSpans.forEach(function (pageNoSpan) {
        pageNoSpan.style.color = 'white';
    });

    selectedSpan.style.color = 'red';
}


//운동 선택 이벤트
let exerciseSelect = document.querySelector('.exercise-select');
document.addEventListener('click', (e) => {
    if (exerciseSelect.style.display == 'flex')//현재div가 운동선택div일때만
    {

        let selectDiv;
        if (e.target.classList.contains('exercise-item')) {   //운동div를 눌렀을때
            selectDiv = e.target; //현재 타겟을 selectDiv에 저장
        }
        else if (e.target.parentNode.classList.contains('exercise-item')) {//운동div의 하위div를 눌렀을때
            selectDiv = e.target.parentNode;  //현재 타겟의 부모 노드를 selectDiv에 저장
        }
        else if (e.target.parentNode.parentNode.classList.contains('exercise-item')) {//운동div의 하위div의 하위div를 눌렀을때
            selectDiv = e.target.parentNode.parentNode;   //현재 타겟의 부모의 부모 노드를 selectDiv에 저장
        }
        if (selectDiv.classList.contains('exercise-item')) {
            //선택 표시된 style들 초기화
            let exerciseItems = document.querySelectorAll('.exercise-item');
            exerciseItems.forEach(function (exerciseItem) {
                exerciseItem.style.backgroundColor = 'black';
            })

            selectDiv.style.backgroundColor = 'red';   //선택 표시

            //input창에 운동이름 넣어주기
            let exerciseName = selectDiv.dataset.name;
            let exerciseNameInpunt = document.getElementById('exercise-name-input');
            exerciseNameInpunt.value = `${exerciseName}`;

            if (setDetailDiv.style.display == 'flex') {
                let setH2 = document.querySelector('.set-detail .title h2')
                setH2.innerHTML = exerciseNameInpunt.value;

                let exerciseNameInp = document.querySelector('.exerciseName');
                exerciseNameInp.value = exerciseNameInpunt.value;
            }
        }
    }
})



//세트 추가 버튼
let setAddBtn = document.querySelector('.setAddBtn');
setAddBtn.addEventListener('click', (e) => {
    let tbody = document.querySelector('.set-setting .set-table tbody');
    let childCount = tbody.childElementCount + 1;

    //innerHTML을 사용하면 새로운 행을 추가할 때, 
    //tbody의 전체 내용을 새로운 HTML 문자열로 대체하기 때문에 이전 행의 기존 입력 값이 손실된다.
    //이 문제를 해결하려면 새로운 행을 추가할 때 기존 행을 대체하는 것이 아니라,
    //새로운 행을 기존 행 뒤에 추가해야 됨.

    let newRow = document.createElement('tr');
    newRow.className = 'set-list';
    newRow.innerHTML = `<td>${childCount}</td>
    <td><input type="text" name="Weight"> kg</td>
    <td><input type="text" name="count"></td>
    <td><input type="checkbox" name="check"></td>`;

    tbody.appendChild(newRow);
})


//세트 삭제 버튼
let setDelBtn = document.querySelector('.setDelBtn');
setDelBtn.addEventListener('click', (e) => {
    let tbody = document.querySelector('.set-setting .set-table tbody');
    let rowCount = tbody.rows.length;

    if (rowCount > 1) {
        tbody.deleteRow(rowCount - 1);
    } else {
        alert("더 이상 삭제할 행이 없습니다.");
    }
})

//계획 등록 버튼 이벤트
let submitBtn = document.querySelector('.submit');
submitBtn.addEventListener('click', (e) => {
    let exerciseName = document.getElementById('exercise-name-input').value;
    // let setlist;

    let dataArray = [];
    let setTableRows = document.querySelectorAll('.set-list'); // 각 행을 선택

    setTableRows.forEach(function (row) {
        let weight = row.querySelector('input[name="Weight"]').value;
        let count = row.querySelector('input[name="count"]').value;
        let checked = row.querySelector('input[name="check"]').checked;

        let rowData = {
            exerciseWeight: weight,
            exerciseCount: count,
            exerciseCheck: checked
        };
        dataArray.push(rowData);
    });

    let pdto = {
        userNo: "1",         //(수정할예정)
        exerciseName: exerciseName,
        planDate: selectDate,
        setVOList: dataArray
    };
    console.log(pdto);
    $.ajax({
        type: 'post',
        url: '/health/planSetting',
        data: JSON.stringify(pdto),
        // async: false,
        contentType: 'application/json',
        // processData: false,
        success: function (pdto) {
            alert('등록성공');
            getEventList();
            closeModal();
        }
    });
})

//set detail창에 뿌려주기
function spreadSetDetail(extendedProps) {
    console.log(extendedProps)

    let planNoInp = document.querySelector('.planNo');
    planNoInp.value = `${extendedProps.planCalendar.planNo}`;
    let exerciseNameInp = document.querySelector('.exerciseName');
    exerciseNameInp.value = `${extendedProps.planCalendar.exerciseName}`;

    let setH2 = document.querySelector('.set-detail .title h2');
    setH2.innerHTML = `${extendedProps.planCalendar.exerciseName}`

    //tbody 초기화
    let setDetailList = document.querySelector('.set-detail .set-table tbody');
    setDetailList.innerHTML = '';

    for (let i = 0; i < extendedProps.exerciseSetList.length; i++) {
        let checked=extendedProps.exerciseSetList[i].exerciseCheck ? "checked":"";

        setDetailList.innerHTML += `<tr data-setNo=${extendedProps.exerciseSetList[i].exerciseSetNo} data-planNo=${extendedProps.exerciseSetList[i].planNo}><td>${i + 1}</td>
        <td><input type="text" class="setDetailWeight" name="Weight" value=${extendedProps.exerciseSetList[i].exerciseWeight} readonly="readonly"> kg</td>
        <td><input type="text" class="setDetailCount" name="count" value=${extendedProps.exerciseSetList[i].exerciseCount} readonly="readonly"></td>
        <td><input type="checkbox" name="check" ${checked}></td></tr>`;
    }
}



function DeletePlan(userPlanNo) {
    $.ajax({
        type: 'get',
        url: `/health/delPlan?userPlanNo=${userPlanNo}`,
        async: true,
        success: function () {
            alert('삭제성공');
            getEventList();
            closeModal();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) { // 비동기 통신이 실패할경우 error 콜백으로 들어옵니다.
            alert("통신 실패.");
        }
    })
}