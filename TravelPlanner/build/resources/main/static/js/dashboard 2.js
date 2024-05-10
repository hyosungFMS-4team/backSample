document.addEventListener('DOMContentLoaded', function () {
  const currentUrl = window.location.pathname;

  const li1 = document.querySelector('body > div > ul > li:nth-child(3)');
  const li2 = document.querySelector('body > div > ul > li:nth-child(4)');
  const li3 = document.querySelector('body > div > ul > li:nth-child(5)');
  if (currentUrl === '/main_dashboard.mustache' || currentUrl === '/') {
    li1.classList.add('menu-chosen');
    li2.classList.remove('menu-chosen');
    li3.classList.remove('menu-chosen');
  } else if (currentUrl === '/map.mustache') {
    li1.classList.remove('menu-chosen');
    li2.classList.add('menu-chosen');
    li3.classList.remove('menu-chosen');
  } else if (currentUrl === '/favourites') {
    li1.classList.remove('menu-chosen');
    li2.classList.remove('menu-chosen');
    li3.classList.add('menu-chosen');
  }

  //   let array = [];
  //   const travelPlanContents = querySelector('.travelPlanContents');
  //   array.forEach(element => {
  //     let div = document.createElement('div');
  //     div.innerHTML = element;
  //     travelPlanContents.appendChild(div);
  //   });
  const content = document.querySelectorAll('.content');
  content.forEach(x => {
    x.addEventListener('mouseover', e => {
      if (e.target === e.currentTarget) {
        e.target.innerHTML = `
              <button>입장하기</button>
              <button>초대하기</button>`;
      }
    });
    x.addEventListener('mouseleave', e => {
      if (e.target === e.currentTarget) {
        e.stopPropagation();
        e.target.innerHTML = `
            <div class="contentTitle">경주여행</div>
            <!-- title-->
            <div class="contentMain">3박 4일</div>
            <!-- content-->
            <div class="contentFooter">4명</div>
            <!-- content-->`;
      }
    });

    x.querySelectorAll('button').forEach(button => {
      button.addEventListener('mouseover', e => {
        e.preventDefault();
      });
    });
  });
});

const addContent = document.querySelector('.addContent');

addContent.addEventListener('click', e => {});
function redirectToView(memberId) {
  // memberId를 사용하여 URL에 쿼리 매개변수를 추가하고 페이지를 이동합니다.
  var url = '/wish/view?memberId=' + memberId;
  window.location.href = url;
}

function insertCode() {
  let modalBox = document.querySelector('.modal-box');
  modalBox.innerHTML = `
  <div class="modal-action">
  <form method="dialog">
    <button class="btn" onclick="closeModal()">X</button>
  </form>
</div>
<div class="invite-title">초대 코드를 입력해주세요.</div>
<form action="/" method="post" class="insert-code">
  <input type="text" placeholder="초대 코드" class="input input-bordered w-full max-w-xs" />
  <button class="btn" type="submit">Button</button>
</form>`;
}

function closeModal() {
  let modalBox = document.querySelector('.modal-box');
  modalBox.innerHTML = `
  <div class="modal-action">
        <form method="dialog">
          <button class="btn">X</button>
        </form>
      </div>
      <button class="btn btn-wide" onclick="location.href='/index.html'">새로운 일정 만들기</button>
      <button class="btn btn-wide" onclick="insertCode()">일정 참여하기</button>
    `;
  modal.style.visibility = visible;
}
