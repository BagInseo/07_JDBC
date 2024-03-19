const deleteBtn = document.querySelector("#deleteBtn");

deleteBtn.addEventListener("click", (e) => {
  const diaryNo = e.target.dataset.diaryNo;
  console.log(diaryNo);
  if (confirm("삭제 하시겠습니까?")) {
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
  } else {
    alert("삭제 취소");
  }
});
