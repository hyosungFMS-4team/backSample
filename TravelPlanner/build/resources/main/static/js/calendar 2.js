let targetDay = [];
let calendar = document.querySelector('.calendar');
const month_names = ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'];
const monthName = document.querySelector('.month-name');
let currentDate = new Date();
let currentMonth = { value: currentDate.getMonth() };
let currentYear = { value: currentDate.getFullYear() };

const todayShowTime = document.querySelector('.time-formate');
const todayShowDate = document.querySelector('.date-formate');

const currshowDate = new Date();
const dayTextFormate = document.querySelector('.day-text-formate');
const timeFormate = document.querySelector('.time-formate');
const dateFormate = document.querySelector('.date-formate');
let weatherData;
let daysAfter3to10 = [];
const apiKey = '2xTwp1TAzVnY9ozldITciD2a1J2Qk69pegRasEyxs7kl4%2Fcp0%2FoQZZjoNKMmLwECCXELHemso6F2r3QRHGJ31w%3D%3D';

window.onload = async () => {
  document.querySelector('.loading2').style.display = 'flex';
  for (let i = 3; i <= 10; i++) {
    const date = new Date();
    date.setDate(date.getDate() + i);
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const day = date.getDate().toString().padStart(2, '0');
    daysAfter3to10.push(`${year}${month}${day}`);
  }
  try {
    generateCalendar(currentMonth.value, currentYear.value);
    weatherData = await getWeather(); // 비동기로 날씨 정보를 가져옴
    displayWeatherInfo();
    document.querySelector('.loading2').style.display = 'none';
    // document.querySelector('.loading').style.display = 'none';
  } catch (error) {
    console.error('Error fetching weather data:', error);
  }
};

const isLeapYear = year => {
  return (year % 4 === 0 && year % 100 !== 0 && year % 400 !== 0) || (year % 100 === 0 && year % 400 === 0);
};
const getFebDays = year => {
  return isLeapYear(year) ? 29 : 28;
};

const getWeatherIcon = weatherInfo => {
  switch (weatherInfo) {
    case '맑음':
      return '<img src="/images/clear-day.svg" alt="" class="weather-icon">'; // 맑은 날씨 아이콘
    case '흐림':
      return '<img src="/images/fog.svg" alt="" class="weather-icon">'; // 흐린 날씨 아이콘
    case '구름많음':
      return '<img src="/images/overcast.svg" alt="" class="weather-icon">'; // 구름 많은 날씨 아이콘
    case ('구름많고 비', '흐리고 비', '구름많고 소나기', '흐리고 소나기'):
      return '<img src="/images/rain.svg" alt="">';
    case ('흐리고 눈', '구름많고 눈'):
      return '<img src="/images/snow.svg" alt="">';
    case ('구름많고 비/눈', '흐리고 비/눈'):
      return '<img src="/images/sleet.svg" alt="">';

    // 기타 다른 날씨 아이콘들에 대한 처리
    default:
      return '';
  }
};
function displayWeatherInfo() {
  const currentDate = new Date();

  // 오늘부터 10일 후까지의 날씨 정보 표시
  for (let i = 0; i <= 10; i++) {
    const date = new Date();
    date.setDate(currentDate.getDate() + i);
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const day = date.getDate().toString().padStart(2, '0');
    const currentDateStr = `${year}${month}${day}`;

    // 해당 날짜의 날씨 정보 가져오기
    const weatherInfo = weatherData.find(data => data.date === currentDateStr);
    // 날씨 정보가 있을 경우 표시
    const weatherElement = document.querySelector(`body > div > div > div.calendar > div.calendar-body > div.calendar-days`);
    weatherElement.childNodes.forEach(x => {
      if (weatherInfo && x.textContent.padStart(2, '0') === day) {
        x.innerHTML += `
              ${getWeatherIcon(weatherInfo.weatherInfo)}
              <span class="rain-percentage">${weatherInfo.rainPercentage}%</span>
            `;
      }
    });
  }
}

const generateCalendar = (month, year) => {
  let calendar_days = document.querySelector('.calendar-days');
  calendar_days.innerHTML = '';
  let calendar_header_year = document.querySelector('#year');
  let days_of_month = [31, getFebDays(year), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

  let currentDate = new Date();

  monthName.innerHTML = month_names[month];

  calendar_header_year.innerHTML = year;

  let first_day = new Date(year, month);

  for (let i = 0; i <= days_of_month[month] + first_day.getDay() - 1; i++) {
    let day = document.createElement('div');

    if (i >= first_day.getDay()) {
      day.innerHTML = i - first_day.getDay() + 1;
      let date = new Date(year, month, i - first_day.getDay() + 1);

      if (date < currentDate) {
        // 현재 날짜보다 이전인 경우 숫자 회색
        day.classList.add('passed-date');
      }
      if (i - first_day.getDay() + 1 === currentDate.getDate() && year === currentDate.getFullYear() && month === currentDate.getMonth()) {
        day.classList.add('current-date');
      }

      if (weatherData) {
        // 여기서 해당 날짜의 날씨 정보를 가져오고 아이콘을 추가합니다.
        const currentDateStr = `${year}${(month + 1).toString().padStart(2, '0')}${(i - first_day.getDay() + 1).toString().padStart(2, '0')}`;
        const weatherInfo = weatherData.find(data => data.date === currentDateStr);

        if (weatherInfo) {
          const weatherIcon = getWeatherIcon(weatherInfo.weatherInfo);
          const rainPercentage = weatherInfo.rainPercentage;
          day.innerHTML += `${weatherIcon}`;
          day.innerHTML += `<span class="rain-percentage">${rainPercentage}%</span>`;
        }
      }
    }

    // 이미 선택된 날짜인지 확인하고 클래스 추가
    const yearStr = String(year);
    const monthStr = String(month + 1).padStart(2, '0');
    const dayStr = day.firstChild ? day.firstChild.textContent : '';
    const currentDateStr = yearStr + '-' + monthStr + '-' + dayStr.padStart(2, '0');
    if (targetDay.includes(currentDateStr)) {
      day.classList.add('picked-date');
    }

    // 선택한 두 날짜 사이의 날짜인지 확인하고 배경 변경
    if (targetDay.length === 2) {
      const [start, end] = targetDay;
      let date = day.firstChild ? day.firstChild.textContent : '';
      const currentDate = new Date(year, month, date, 9);
      let startDate = new Date(start).setHours(10);
      let endDate = new Date(end);
      //취소하고 앞에날짜 선택하면 큰날짜로 비교
      if (startDate > endDate) {
        let temp;
        temp = startDate;
        startDate = endDate;
        endDate = temp;
      }
      // console.log(currentDate, Date.parse(currentDate), Date.parse(startDate))
      // console.log(Date.parse(currentDate) > Date.parse(startDate))
      if (currentDate > startDate && currentDate < endDate && day.innerHTML) {
        day.classList.add('between-date');
      }
    }

    calendar_days.appendChild(day);
    chooseDate(calendar_days);
  }
};

document.querySelector('#pre-month').onclick = () => {
  if (currentMonth.value === 0) {
    currentYear.value--;
    currentMonth.value = 11;
  } else {
    currentMonth.value--;
  }
  generateCalendar(currentMonth.value, currentYear.value);
};
document.querySelector('#next-month').onclick = () => {
  if (currentMonth.value === 11) {
    currentYear.value++;
    currentMonth.value = 0;
  } else {
    currentMonth.value++;
  }
  generateCalendar(currentMonth.value, currentYear.value);
};
document.querySelector('#pre-year').onclick = () => {
  --currentYear.value;
  generateCalendar(currentMonth.value, currentYear.value);
};
document.querySelector('#next-year').onclick = () => {
  ++currentYear.value;
  generateCalendar(currentMonth.value, currentYear.value);
};

const showCurrentDateOption = {
  year: 'numeric',
  month: 'long',
  day: 'numeric',
  weekday: 'long',
};
const currentDateFormate = new Intl.DateTimeFormat('ko-KR', showCurrentDateOption).format(currshowDate);
console.log(currentDateFormate);
todayShowDate.textContent = currentDateFormate;
const today = convertDateFormat(currentDateFormate);

setInterval(() => {
  const timer = new Date();
  const option = {
    hour: 'numeric',
    minute: 'numeric',
    second: 'numeric',
  };
  const formateTimer = new Intl.DateTimeFormat('ko-KR', option).format(timer);
  todayShowTime.textContent = formateTimer;
}, 10);

// main
function goMainDashBoard() {
  window.location.href = 'main_dashboard.mustache';
}

function chooseDate(calendarElement) {
  const picked = calendarElement.querySelectorAll('.calendar-days > div');

  // 이전에 등록된 이벤트 핸들러 제거
  picked.forEach(p => {
    p.removeEventListener('click', handleDateClick);
  });

  picked.forEach(p => {
    p.addEventListener('click', handleDateClick);
  });
}

function handleDateClick(e) {
  const target = e.target;
  const calendarElement = target.closest('.calendar');
  const alreadyPickedCount = calendarElement.querySelectorAll('.picked-date').length;
  const year = calendarElement.querySelector('#year').textContent;
  const month = monthName.textContent.replace('월', '');
  const form = document.getElementsByName('targetDay');
  // 이미 선택된 날짜를 다시 선택한 경우 클래스 제거
  if (target.classList.contains('picked-date')) {
    target.classList.remove('picked-date');
    const year = calendarElement.querySelector('#year').textContent;
    const month = monthName.textContent.replace('월', '');
    const date = target.firstChild ? target.firstChild.textContent : '';
    const index = targetDay.indexOf(year + '-' + month.padStart(2, '0') + '-' + date.padStart(2, '0'));
    if (index !== -1) {
      targetDay.splice(index, 1);
    }
    // 선택한 날짜를 변경했으므로 배경색 다시 설정
    resetBackground(calendarElement);
    return;
  }
  let date = target.firstChild ? target.firstChild.textContent.trim() : '';
  const chosenDate = year + '-' + month.padStart(2, '0') + '-' + date.padStart(2, '0');
  if (
    Date.parse(currentDate) <= Date.parse(chosenDate) &&
    alreadyPickedCount < 2 &&
    !target.classList.contains('picked-date') &&
    target.innerHTML.trim() &&
    targetDay.length < 2
  ) {
    targetDay.push(chosenDate);
    target.classList.add('picked-date');
    //localstorage
    localStorage.setItem('targetDay', JSON.stringify(targetDay));
    form.value = targetDay;
    // 선택한 날짜를 변경했으므로 배경색 다시 설정
    resetBackground(calendarElement);
  }

  // 이미 두 개의 날짜가 선택된 경우 클릭 이벤트 무시
  if (alreadyPickedCount >= 2) {
    return;
  }

  // 빈 날짜를 클릭한 경우 클릭 이벤트 무시
  if (!target.innerHTML.trim()) {
    return;
  }

  // 선택한 두 날짜 사이의 날짜들의 배경 변경
  if (targetDay.length === 2) {
    const [start, end] = targetDay;
    // 문자열에서 아이콘을 제거하여 새로운 문자열을 반환
    const days = calendarElement.querySelectorAll('.calendar-days > div');
    days.forEach(day => {
      let date = day.firstChild ? day.firstChild.textContent : '';
      const currentDate = new Date(year, month - 1, date, 9);
      let startDate = new Date(start);
      let endDate = new Date(end);

      //취소하고 앞에날짜 선택하면 큰날짜로 비교
      if (startDate > endDate) {
        let temp;
        temp = startDate;
        startDate = endDate;
        endDate = temp;
      }
      if (currentDate > startDate && currentDate < endDate && day.innerHTML && !day.classList.contains("picked-date")) {
        day.classList.add('between-date');
      } else {
        day.classList.remove('between-date');
      }
    });
  }
}

function resetBackground(calendarElement) {
  const days = calendarElement.querySelectorAll('.calendar-days > div');
  days.forEach(day => {
    day.classList.remove('between-date');
  });
}

function convertDateFormat(dateString) {
  // 주어진 날짜 문자열을 공백을 기준으로 분리하여 각 부분을 배열에 저장합니다.
  const parts = dateString.split(' ');

  // 연도, 월, 일 부분을 추출합니다.
  const year = parts[0].replace('년', ''); // '2024'
  const month = parts[1].replace('월', '').padStart(2, '0'); // '05'
  const day = parts[2].replace('일', '').padStart(2, '0'); // '06'

  // YYYYMMDD 형식의 문자열을 반환합니다.
  return year + month + day;
}

//오늘날짜 기준 3~10일후까지 날씨 오픈 api
async function getWeather() {
  const date = new Date();
  let today = `${date.getFullYear()}${(date.getMonth() + 1).toString().padStart(2, '0')}${date.getDate().toString().padStart(2, '0')}`;
  const apiUrl = 'https://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst';
  const queryParams = `?serviceKey=${apiKey}&numOfRows=10&dataType=JSON&pageNo=1&regId=11B00000&tmFc=${today}0600`;
  try {
    const response = await fetch(apiUrl + queryParams);
    const data = await response.json();
    const res = data.response.body.items.item[0];
    let weatherDataObject = mapWeatherData(res, daysAfter3to10);
    let shortForecast = await getTemperature();
    let resultObject = shortForecast.concat(weatherDataObject);
    return resultObject;
  } catch (error) {
    console.error('Error fetching weather data:', error);
  }
}

// 응답 데이터와 날짜 배열을 매핑하여 날씨 정보를 가져오는 함수
function mapWeatherData(res, dates) {
  const mappedData = [];
  dates.forEach((date, idx) => {
    const weatherInfo = {
      date,
      rainPercentage: res[`rnSt${idx + 3}Am`] ? res[`rnSt${idx + 3}Am`] : res[`rnSt${idx + 3}`],
      weatherInfo: res[`wf${idx + 3}Am`] ? res[`wf${idx + 3}Am`] : res[`wf${idx + 3}`],
    };
    mappedData.push(weatherInfo);
  });
  return mappedData;
}
async function getTemperature() {
  const date = new Date();
  let today = `${date.getFullYear()}${(date.getMonth() + 1).toString().padStart(2, '0')}${date.getDate().toString().padStart(2, '0')}`;
  let dayafter = `${date.getFullYear()}${(date.getMonth() + 1).toString().padStart(2, '0')}${(date.getDate() + 3).toString().padStart(2, '0')}`;
  const apiUrl = 'http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst';
  const queryParams = `?serviceKey=${apiKey}&numOfRows=810&fcstDate=${dayafter}&fcstTime=0500&dataType=JSON&pageNo=1&base_date=${today}&base_time=0500&nx=76&ny=114`;

  try {
    const response = await fetch(apiUrl + queryParams);
    const data = await response.json();
    const res = data.response.body.items.item
      .filter(x => (x.category === 'SKY' || x.category === 'POP') && x.fcstTime === '0600')
      .map(k => {
        const { fcstDate, category, fcstValue } = k;
        return { date: fcstDate, category, fcstValue };
      });

    const transformedData = res.reduce((acc, item) => {
      if (item.category === 'SKY') {
        acc[item.date] = {
          weatherInfo: skyState(parseInt(item.fcstValue)),
          rainPercentage: acc[item.date] ? acc[item.date].rainPercentage : null,
        };
      } else if (item.category === 'POP') {
        acc[item.date] = {
          ...acc[item.date],
          rainPercentage: item.fcstValue,
          weatherInfo: acc[item.date] ? acc[item.date].weatherInfo : null,
        };
      }
      return acc;
    }, {});

    const weatherInfo = Object.entries(transformedData).map(([date, info]) => ({
      date,
      rainPercentage: Number(info.rainPercentage),
      weatherInfo: info.weatherInfo,
    }));

    return weatherInfo;
  } catch (error) {
    console.error('Error fetching weather data:', error);
  }
}

function skyState(arg) {
  let state;
  arg = Number(arg);
  switch (true) {
    case arg <= 5:
      state = '맑음';
      break;
    case arg <= 8:
      state = '구름많음';
      break;
    case arg <= 10:
      state = '흐림';
      break;
  }
  return state;
}
