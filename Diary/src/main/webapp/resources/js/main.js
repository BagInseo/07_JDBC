const title = document.querySelector("#diaryTitle");
const content = document.querySelector("#diaryContent");

const inputForm = document.inputForm;
inputForm.addEventListener("submit", (e) => {
  if (title.value.trim().length === 0) {
    alert("제목을 입력하세요");
    e.preventDefault();
    return;
  }
  if (content.value.trim().length === 0) {
    alert("일기를 작성하세요");
    e.preventDefault();
    return;
  }
});
