const deleteBtnList = document.querySelectorAll(".delete-btn");

for (let btn of deleteBtnList) {
  btn.addEventListener("click", (e) => {
    const diaryNo = e.target.closest("tr").children[0].innerText;
    console.log(diaryNo);

    if (confirm(`${diaryNo} 번 일기를 삭제 하시겠습니까?`)) {
      const form = document.createElement("form");
      form.action = "/diary/diaryDelete";
      form.method = "POST";

      const input = document.createElement("input");
      input.type = "hidden";
      input.value = diaryNo;
      input.name = "diaryNo";

      form.append(input);

      document.querySelector("body").append(form);

      form.submit();

      console.log(form);
    } else {
      alert("삭제 취소");
    }
  });
}
