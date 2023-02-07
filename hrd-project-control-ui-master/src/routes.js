import React from "react";

const Dashboard = React.lazy(() => import("./views/dashboard/Dashboard"));
const Login = React.lazy(() => import("./views/login/Login"));
const page403 = React.lazy(() => import("./views/page_not_found/403"));

//SCHEDULE
const SheduleType = React.lazy(() =>
  import("./views/schedule/NewScheduleType")
);
const ProShedule = React.lazy(() =>
  import("./views/schedule/projectSchedule/NewProjectSchedule")
);
const DocShedule = React.lazy(() =>
  import("./views/schedule/documentSchedule/NewDocumentSchedule")
);
const ViewDocumentShcedule = React.lazy(() =>
  import("./views/schedule/documentSchedule/viewDocumentSchdeule")
);
const AddNewDocumentSchedule = React.lazy(() =>
  import("./views/schedule/documentSchedule/AddNewDocumentSchedule")
);
const ViewProjectShcedule = React.lazy(() =>
  import("./views/schedule/projectSchedule/viewProjectSchedule")
);
const AddNewProjectSchedule = React.lazy(() =>
  import("./views/schedule/projectSchedule/AddNewProjectSchedule")
);

//PROJECT DIVISION
const ProjectDivision = React.lazy(() =>
  import("./views/projectDivision/ProjectDivision")
);
const NewProject = React.lazy(() =>
  import("./views/projectDivision/NewProject")
);

const ViewProjectDivision = React.lazy(() =>
  import("./views/projectDivision/viewProjectDivision")
);
const AssignProject = React.lazy(() =>
  import("./views/projectDivision/AssignProject")
);
const NewAssignProject = React.lazy(() =>
  import("./views/projectDivision/NewAssignProject")
);
const UpdateProject = React.lazy(() =>
  import("./views/projectDivision/UpdateProject")
);

const Project = React.lazy(() => import("./views/projectDivision/Project"));

//TASK
const Task = React.lazy(() => import("./views/task/Task"));
const ViewTask = React.lazy(() => import("./views/task/Taskview"));
const ViewAllTask = React.lazy(() => import("./views/task/ViewAllTask"));
const ViewMyTask = React.lazy(() => import("./views/task/ViewMyTask"));
const AddTask = React.lazy(() => import("./views/task/TaskAssign"));
const EditTask = React.lazy(() => import("./views/task/EditTask"));

//USER
const User = React.lazy(() => import("./views/userControl/UserControl"));
const Generation = React.lazy(() =>
  import("./views/userControl/generation/Generation")
);
const University = React.lazy(() =>
  import("./views/userControl/university/University")
);

const NewUser = React.lazy(() => import("./views/userControl/NewUser"));

const UpdateUser = React.lazy(() => import("./views/userControl/UpdateUser"));

//DAILY REPORT
const DailyReport = React.lazy(() => import("./views/dailyReport/DailyReport"));
const ViewDailyReport = React.lazy(() =>
  import("./views/dailyReport/viewDailyReport")
);
const routes = [
  { path: "/", exact: true, name: "Home" },
  { path: "/dashboard", name: "Dashboard", component: Dashboard },
  { path: "/login", name: "Login", component: Login },
  { path: "/page403", name: "page403", component: page403 },

  //Schedule
  {
    path: "/schedule",
    name: "Schedule",
    component: SheduleType,
    exact: true,
  },
  {
    path: "/schedule/schedule_type",
    name: "Schedule Type",
    component: SheduleType,
  },
  {
    path: "/schedule/project_schedule",
    name: "Project Schedule",
    component: ProShedule,
  },
  {
    path: "/schedule/document_schedule",
    name: "Documnet Schedule",
    component: DocShedule,
  },
  {
    path: "/schedule/view_document_schedule/:id",
    name: "View Document Schedule",
    component: ViewDocumentShcedule,
  },
  {
    path: "/schedule/add_new_document_schedule",
    name: "Add Document Schedule",
    component: AddNewDocumentSchedule,
  },
  {
    path: "/schedule/view_project_schedule/:id",
    name: "View Project Schedule",
    component: ViewProjectShcedule,
  },
  {
    path: "/schedule/add_new_project_schedule",
    name: "Add  Schedule",
    component: AddNewProjectSchedule,
  },

  //Project Division
  {
    path: "/project_division",
    name: "Project Division",
    component: ProjectDivision,
    exact: true,
  },
  {
    path: "/project_division/project",
    name: "Project",
    component: Project,
  },
  {
    path: "/project_division/view_project_division",
    name: "View Project Divison",
    component: ProjectDivision,
  },
  {
    path: "/project_division/view_projects_division/:id",
    name: "View Projects Divison",
    component: ViewProjectDivision,
  },
  {
    path: "/project_division/assign_project",
    name: "Assign Project",
    component: AssignProject,
  },
  {
    path: "/project_division/new_assign_project/:id",
    name: "New Assign Project",
    component: NewAssignProject,
  },
  {
    path: "/project_division/new_project",
    name: "New Project",
    component: NewProject,
  },
  {
    path: "/project_division/update_project/:id",
    name: "Update Project",
    component: UpdateProject,
  },

  //Task
  {
    path: "/task",
    name: "Task Management",
    component: Task,
    exact: true,
  },

  {
    path: "/task/view_task/:projectId",
    name: "View Task",
    component: ViewTask,
    exact: true,
  },
  {
    path: "/task/view_all_task/:show_pId",
    name: "View All Task",
    component: ViewAllTask,
    exact: true,
  },
  {
    path: "/task/view_my_task/:show_pId",
    name: "View My Task",
    component: ViewMyTask,
    exact: true,
  },
  {
    path: "/task/add_task/:id",
    name: "Assign Task",
    component: AddTask,
    exact: true,
  },

  {
    path: "/task/edit_task/:id",
    name: "Edit Task",
    component: EditTask,
    exact: true,
  },
  //User
  {
    path: "/user_control",
    name: "User Control",
    component: User,
    exact: true,
  },
  {
    path: "/user_control/user",
    name: "User",
    component: User,
  },
  {
    path: "/user_control/new_user",
    name: "Add New User",
    component: NewUser,
  },
  {
    path: "/user_control/update_user/:id",
    name: "Update User",
    component: UpdateUser,
  },
  {
    path: "/user_control/generation",
    name: "Generation",
    component: Generation,
  },
  {
    path: "/user_control/university",
    name: "University",
    component: University,
  },

  //Daily report
  {
    path: "/daily_report",
    name: "Daily Report",
    component: DailyReport,
  },
  {
    path: "/viewDailyReport/:id",
    name: "View Daily Report",
    component: ViewDailyReport,
  },
];

export default routes;
