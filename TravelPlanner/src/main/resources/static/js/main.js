const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');
const places = [
  {
    imgSrc: 'images/kyeongju.png',
    title: '과거와 현대가 만나 신비로운 경주의 밤',
    content: `밤의 경주를 상상해 본 적 있나요? 경주는 조상들의 예술 감각에 현대적인 조명을 더한 독특한 야경 풍경을 감상할 수 있는 곳이에요. 동궁과 월지, 월정교, 첨성대 등은 낮에도 아름답지만, 밤에는 더욱더 매력적이에요.`,
  },
  {
    imgSrc: 'images/busan.png',
    title: '활기 넘치는 휴가를 보낼 수 있는 곳 부산',
    content: `푸르른 바다와 대도시의 편리함을 좋아하는 여행객들에게 부산은 천국이죠. 탁 트인 바다 전경과 주변 관광명소를 한눈에 담으실 수 있어요.`,
  },
  {
    imgSrc: 'images/yeosu.png',
    title: '역사와 문화가 살아 숨 쉬는 여수',
    content: `역사, 문화, 자연을 두루 즐길 수 있는 토탈 패키지, 여수 여행 준비되셨나요? 천천히 여수 하멜 등대, 진남관, 이순신 광장, 종포 해양공원에도 가보세요.`,
  },
  {
    imgSrc: 'images/suwon.png',
    title: '눈과 입이 즐거운 곳 수원',
    content: `보고 느끼고 맛보는 오감만족 여행지 수원으로 떠나보세요. 요즘 한옥의 단아한 매력에 빠지셨다면 도심에서도 특별한 전통 한옥을 볼 수 있는 수원 화성을 추천해 드려요.`,
  },
  {
    imgSrc: 'images/sokcho.png',
    title: '인생샷을 남길 수 있는 곳, "속초"',
    content: `멋진 여행도 아기자기한 여행도 모두 선사하는 속초로 출발! 힘들게 등산을 하지 않고 설악산의 풍경을 한눈에 보고 싶다면 케이블카를 이용해 보세요.`,
  },
  {
    imgSrc: 'images/jeju.png',
    title: '가까이에서 이국의 매력을 느낄 수 있는 제주',
    content: `특별한 여행 사진과 추억을 남길 수 있는 명소가 곳곳에 가득해요. 말 모양의 등대가 유명한 이호테우해변, 특이한 LED 모형이 가득한 수목원테마파크 또는 무지개색 방호벽이 펼쳐지는 도두동 무지개 해안도로에서 인증샷을 남기거나 가볍게 산책을 즐겨보세요.`,
  },
];

let splide = new Splide('#top', {
  type: 'loop',
  perPage: 5,
  width: '87%',
  fixedWidth: '200px',
  fixedHeight: '200px',
  gap: '20px',
  pauseOnHover: true,
  autoScroll: {
    speed: 1.8,
  },
});
let splide2 = new Splide('#bottom', {
  type: 'loop',
  perPage: 5,
  width: '87%',
  fixedWidth: '200px',
  fixedHeight: '200px',
  gap: '20px',
  pauseOnHover: true,
  direction: 'rtl',
  autoScroll: {
    speed: 1.8,
  },
});
signUpButton.addEventListener('click', () => {
  container.classList.add('right-panel-active');
});

signInButton.addEventListener('click', () => {
  container.classList.remove('right-panel-active');
});

function getFormData() {
  return {
    memberId: document.querySelector('#id').value,
    password: document.getElementById('password').value,
    email: document.getElementById('email').value,
    phone: document.getElementById('phoneNumber').value,
    name: document.getElementById('name').value,
    gender: document.getElementById('gender').value,
    age: document.getElementById('age').value,
  };
}

document.addEventListener('DOMContentLoaded', function () {
  splide.mount(window.splide.Extensions);
  splide2.mount(window.splide.Extensions);

  const idInput = document.getElementById('id');
  idInput.addEventListener('focusout', function () {
    let id = idInput.value;
    if (id === '' || id.length === 0) {
      document.getElementById('label1').style.color = 'red';
      document.getElementById('label1').textContent = '공백은 ID로 사용할 수 없습니다.';
      return false;
    }

    fetch('/signup/memberid-check', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ memberId: id }), // memberId 값을 인코딩하여 전달
    })
        .then(response => {
          console.log(response);
          if (response.ok) {
            return response.json();
          } else {
            throw new Error('Network response was not ok.');
          }
        })
        .then(result => {
          if (result === true) {
            document.getElementById('label1').style.color = 'black';
            document.getElementById('label1').textContent = '사용 가능한 ID 입니다.';
          } else {
            document.getElementById('label1').style.color = 'red';
            document.getElementById('label1').textContent = '사용 불가능한 ID 입니다.';
          }
        })
        .catch(error => {
          console.error('There was a problem with the fetch operation:', error);
        });
  });
  let signUpBtn = document.querySelector('#signUpBtn');
  // let formData = getFormData(); // 폼 데이터를 가져옵니다.
  signUpBtn.addEventListener('click', function () {
    let jsonFormData = getFormData(); // JSON 문자열로 변환합니다.
    console.log(jsonFormData)
    // 서버로 전송합니다. 예를 들어 fetch를 사용할 수 있습니다.
    fetch('/signup/member', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(jsonFormData),
    })
        .then(response => {
          if (response.ok) {
            console.log(response)
            // 성공적으로 요청이 처리된 경우에 대한 처리를 추가할 수 있습니다.
            // window.location.href = "https://localhost:8080";
          } else {
            // 오류 응답에 대한 처리를 추가할 수 있습니다.
            // window.location.href = "https://localhost:8080";

            console.error('회원가입 실패');
            console.log(response)
          }
        })
        .catch(error => {
          console.error('네트워크 오류:', error);
        });
  });
  // 폼 요소들의 값을 가져와서 JSON 객체로 만듭니다.
});

const login = document.querySelector('#loginBtn');
login.addEventListener('click', function () {
  let id = document.querySelector("#loginId");
  let password = document.querySelector("#loginPassword");
  fetch('/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ memberId: id.value,
    password : password.value}), // memberId 값을 인코딩하여 전달
  })
      .then(response => {
        console.log(response);
        if (response.ok) {
          return response.text();
        } else {
          throw new Error('Network response was not ok.');
        }
      })
      .then(result => {
        if (result === true) {
          document.getElementById('label1').style.color = 'black';
          document.getElementById('label1').textContent = '사용 가능한 ID 입니다.';
        } else {
          document.getElementById('label1').style.color = 'red';
          document.getElementById('label1').textContent = '사용 불가능한 ID 입니다.';
        }
      })
      .catch(error => {
        console.error('There was a problem with the fetch operation:', error);
      });
});


const checkPassword = () => {
  let password = document.getElementById('password');
  let passwordChk = document.getElementById('passwordChk');
  if (passwordChk.value === password.value) {
    document.getElementById('label3').style.color = 'green';
    document.getElementById('label3').textContent = '비밀번호가 일치합니다.';
    document.querySelector('.submitBtn').disabled = false;
  } else {
    document.getElementById('label3').style.color = 'red';
    document.getElementById('label3').textContent = '비밀번호가 일치하지 않습니다.';
    document.querySelector('.submitBtn').disabled = true;
  }
};

const openModal = () => {
  const modal = document.querySelector('.modal');
  modal.style.display = 'flex';
};

const closeModal = () => {
  const modal = document.querySelector('.modal');
  modal.style.display = 'none';
};

window.onload = () => {
  const Slider = function (pages, pagination) {
    let slides = [],
      btns = [],
      count = 0,
      current = 0,
      touchstart = 0,
      animation_state = false;

    const init = () => {
      slides = pages.children;
      count = slides.length;
      for (let i = 0; i < count; i++) {
        slides[i].style.bottom = -(i * 100) + '%';
        let btn = document.createElement('li');
        let downDiv = document.createElement('div');
        let img = document.createElement('img');
        img.src = 'images/down.png';
        downDiv.className = 'down';
        downDiv.appendChild(img);
        slides[i].appendChild(downDiv);
        downDiv.addEventListener('click', gotoNext);
        btn.dataset.slide = i;
        btn.addEventListener('click', btnClick);
        btns.push(btn);
        pagination.appendChild(btn);
      }

      btns[0].classList.add('active');
    };

    const gotoNum = index => {
      if (index != current && !animation_state) {
        animation_state = true;
        setTimeout(() => (animation_state = false), 500);
        btns[current].classList.remove('active');
        current = index;
        btns[current].classList.add('active');
        for (let i = 0; i < count; i++) {
          slides[i].style.bottom = (current - i) * 100 + '%';
        }
      }
    };

    const gotoNext = () => (current < count - 1 ? gotoNum(current + 1) : false);
    const gotoPrev = () => (current > 0 ? gotoNum(current - 1) : false);
    const btnClick = e => gotoNum(parseInt(e.target.dataset.slide));
    pages.ontouchstart = e => (touchstart = e.touches[0].screenY);
    pages.ontouchend = e => (touchstart < e.changedTouches[0].screenY ? gotoPrev() : gotoNext());
    pages.onmousewheel = pages.onwheel = e => (e.deltaY < 0 ? gotoPrev() : gotoNext());
    const down = () => {
      gotoNext(); // Slider 객체의 gotoNext 메서드 호출
    };

    init();
  };

  let pages = document.querySelector('.pages');
  let pagination = document.querySelector('.pagination');
  let slider = new Slider(pages, pagination);
};

const slidesContainer = document.querySelector('#slides-container');
places.forEach(x => {
  const slideContent = document.createElement('li');
  slideContent.className = 'slide';
  slideContent.innerHTML = `
  <div class="second-left">
      <img src="/${x.imgSrc}" alt="" />
  </div>
  <div class="second-right">
      <div class="title">${x.title}</div>
      <div>
      ${x.content}
      </div>
`;
  slidesContainer.appendChild(slideContent);
});
const slide = document.querySelector('.slide');
const prevButton = document.getElementById('slide-arrow-prev');
const nextButton = document.getElementById('slide-arrow-next');

nextButton.addEventListener('click', () => {
  const slideWidth = slide.clientWidth;
  slidesContainer.scrollLeft += slideWidth;
});

prevButton.addEventListener('click', () => {
  const slideWidth = slide.clientWidth;
  slidesContainer.scrollLeft -= slideWidth;
});

//active 바뀔때도 이벤트 적용
splide.on('active', function (x) {
  const cities = document.querySelectorAll('.city');
  cities.forEach(x => {
    const div = document.createElement('div');
    let src = x.firstChild.getAttribute('src').replace('/images/','').replace('.png', '');
    div.innerText = `${src}`;
    div.classList.add('hovered');
    x.addEventListener('mouseenter', e => {
      x.appendChild(div);
    });
    x.addEventListener('mouseleave', e => {
      div.remove();
    });
    x.addEventListener('click', e => {
      window.open(`https://map.kakao.com/?q=${src}`);
    });
  });
});
