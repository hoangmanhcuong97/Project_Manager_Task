function deleteTask(id) {
    console.log("tao la id day: "+id);
    $.getJSON("/delete-task/" + id)
}