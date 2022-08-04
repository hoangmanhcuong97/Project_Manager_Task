function deleteTask(id) {
    console.log("tao kla id day: "+id);
    $.getJSON("/delete-task/" + id)
}