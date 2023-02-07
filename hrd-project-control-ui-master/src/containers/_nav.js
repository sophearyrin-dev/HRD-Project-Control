export default [
  {
    _tag: "CSidebarNavItem",
    name: "Dashboard",
    to: "/dashboard",
    fontIcon: "fas fa-tachometer-alt",
  },
  {
    _tag: "CSidebarNavDropdown",
    name: "Shcedule",
    route: "/schedule",
    fontIcon: "far fa-calendar-alt",
    _children: [
      {
        _tag: "CSidebarNavItem",
        name: "Schedule Type",
        to: "/schedule/schedule_type",
      },
      {
        _tag: "CSidebarNavItem",
        name: "Project Schedule",
        to: "/schedule/project_schedule",
      },
      {
        _tag: "CSidebarNavItem",
        name: "Document Schedule",
        to: "/schedule/document_schedule",
      },
    ],
  },
  {
    _tag: "CSidebarNavDropdown",
    name: "Project Divison",
    route: "/project_division",
    fontIcon: "fab fa-product-hunt",
    _children: [
      {
        _tag: "CSidebarNavItem",
        name: "View Project Division",
        to: "/project_division/view_project_division",
      },
      {
        _tag: "CSidebarNavItem",
        name: "Project",
        to: "/project_division/project",
      },
      {
        _tag: "CSidebarNavItem",
        name: "Assign Project",
        to: "/project_division/assign_project",
      },
    ],
  },

  {
    _tag: "CSidebarNavDropdown",
    name: "User Control",
    route: "/user_control",
    fontIcon: "fas fa-user",
    _children: [
      {
        _tag: "CSidebarNavItem",
        name: "User",
        to: "/user_control/user",
      },
      {
        _tag: "CSidebarNavItem",
        name: "Generation",
        to: "/user_control/generation",
      },
      {
        _tag: "CSidebarNavItem",
        name: "University",
        to: "/user_control/university",
      },
    ],
  },

  {
    _tag: "CSidebarNavItem",
    name: "Task Managment",
    to: "/task",
    fontIcon: "fas fa-tasks",
  },

  {
    _tag: "CSidebarNavItem",
    name: "Daily Report",
    to: "/daily_report",
    fontIcon: "fas fa-clipboard-list",
  },
];
