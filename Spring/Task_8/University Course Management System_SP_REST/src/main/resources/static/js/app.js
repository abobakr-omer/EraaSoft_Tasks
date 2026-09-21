/* ============================================================
   app.js - UI wiring for the University Course Management API
   ============================================================ */

document.addEventListener('DOMContentLoaded', () => {
    initTheme();
    initTabs();
    initModal();
    initForms();
    initRefreshButtons();
    reloadAll();
});

/* ------------------------------------------------------------
   escaping - every value from the API goes through this
   ------------------------------------------------------------ */

function esc(value) {
    if (value === null || value === undefined) return '';
    return String(value)
        .replace(/&/g, '&amp;')
        .replace(/</g, '&lt;')
        .replace(/>/g, '&gt;')
        .replace(/"/g, '&quot;')
        .replace(/'/g, '&#39;');
}

/* ------------------------------------------------------------
   theme
   ------------------------------------------------------------ */

function initTheme() {

    const root = document.documentElement;
    const toggle = document.getElementById('theme-toggle');

    let saved = null;
    try {
        saved = localStorage.getItem('ucm-theme');
    } catch (e) { /* storage blocked - fall back to light */ }

    const initial = saved || 'light';
    root.setAttribute('data-theme', initial);
    updateThemeIcon(initial);

    toggle.addEventListener('click', () => {
        const next = root.getAttribute('data-theme') === 'dark' ? 'light' : 'dark';
        root.setAttribute('data-theme', next);
        updateThemeIcon(next);
        try {
            localStorage.setItem('ucm-theme', next);
        } catch (e) { /* ignore */ }
    });
}

function updateThemeIcon(theme) {
    document.querySelector('.theme-icon').innerHTML = theme === 'dark' ? '&#9790;' : '&#9788;';
}

/* ------------------------------------------------------------
   tabs
   ------------------------------------------------------------ */

function initTabs() {
    document.querySelectorAll('.tab').forEach(tab => {
        tab.addEventListener('click', () => {

            document.querySelectorAll('.tab').forEach(t => t.classList.remove('is-active'));
            document.querySelectorAll('.panel').forEach(p => p.classList.remove('is-active'));

            tab.classList.add('is-active');
            document.getElementById('panel-' + tab.dataset.tab).classList.add('is-active');
        });
    });
}

/* ------------------------------------------------------------
   toasts
   ------------------------------------------------------------ */

function toast(type, title, message) {

    const el = document.createElement('div');
    el.className = 'toast toast-' + type;
    el.innerHTML = `
        <div class="toast-title">${esc(title)}</div>
        <div class="toast-msg">${esc(message)}</div>`;

    document.getElementById('toasts').appendChild(el);

    setTimeout(() => el.remove(), 4500);
}

/* ------------------------------------------------------------
   forms
   ------------------------------------------------------------ */

function initForms() {

    onSubmit('student-form', async (form) => {
        await API.students.create({
            name:  form.name.value.trim(),
            email: form.email.value.trim()
        });
        toast('success', 'Student created', form.name.value.trim());
        form.reset();
        await reloadAll();
    });

    onSubmit('course-form', async (form) => {
        await API.courses.create({
            courseCode:  form.courseCode.value.trim(),
            title:       form.title.value.trim(),
            description: form.description.value.trim()
        });
        toast('success', 'Course created', form.title.value.trim());
        form.reset();
        await reloadAll();
    });

    onSubmit('instructor-form', async (form) => {
        await API.instructors.create({
            name:  form.name.value.trim(),
            email: form.email.value.trim()
        });
        toast('success', 'Instructor created', form.name.value.trim());
        form.reset();
        await reloadAll();
    });

    onSubmit('register-form', async (form) => {
        await API.students.register(form.studentId.value, form.courseId.value);
        toast('success', 'Registered', 'Student registered in the course.');
        await reloadAll();
    });

    onSubmit('assign-form', async (form) => {
        await API.courses.assign(form.courseId.value, form.instructorId.value);
        toast('success', 'Instructor assigned', 'The course was updated.');
        await reloadAll();
    });

    onSubmit('taught-form', async (form) => {
        const courses = await API.instructors.taught(form.instructorId.value);
        renderTaughtCourses(courses);
    });
}

/**
 * Wires a submit handler with disabled-button state and error toasts,
 * so no individual handler has to repeat that boilerplate.
 */
function onSubmit(formId, handler) {

    const form = document.getElementById(formId);

    form.addEventListener('submit', async (event) => {
        event.preventDefault();

        const button = form.querySelector('button[type="submit"]');
        const original = button.textContent;

        button.disabled = true;
        button.textContent = 'Working...';

        try {
            await handler(form);
        } catch (error) {
            toast('error', 'Request failed', error.message);
        } finally {
            button.disabled = false;
            button.textContent = original;
        }
    });
}

function initRefreshButtons() {
    document.querySelectorAll('[data-refresh]').forEach(button => {
        button.addEventListener('click', () => reloadAll());
    });
}

/* ------------------------------------------------------------
   loading + rendering lists
   ------------------------------------------------------------ */

async function reloadAll() {

    const [students, courses, instructors] = await Promise.all([
        API.students.all().catch(err => err),
        API.courses.all().catch(err => err),
        API.instructors.all().catch(err => err)
    ]);

    renderStudents(students);
    renderCourses(courses);
    renderInstructors(instructors);

    fillSelect('students',    students,    s => `#${s.id} - ${s.name}`);
    fillSelect('courses',     courses,     c => `#${c.id} - ${c.courseCode} ${c.title}`);
    fillSelect('instructors', instructors, i => `#${i.id} - ${i.name}`);
}

function emptyState(message) {
    return `<div class="empty"><span class="empty-icon">&#9744;</span>${esc(message)}</div>`;
}

function errorState(error) {
    return `<div class="empty"><span class="empty-icon">&#9888;</span>${esc(error.message)}</div>`;
}

function renderStudents(students) {

    const host = document.getElementById('students-table');

    if (students instanceof Error) { host.innerHTML = errorState(students); return; }
    if (!students.length)          { host.innerHTML = emptyState('No students yet. Create one above.'); return; }

    host.innerHTML = `
        <table>
            <thead>
                <tr><th>ID</th><th>Name</th><th>Email</th><th>Courses</th><th></th></tr>
            </thead>
            <tbody>
                ${students.map(s => `
                    <tr>
                        <td class="col-id">${esc(s.id)}</td>
                        <td>${esc(s.name)}</td>
                        <td>${esc(s.email)}</td>
                        <td><span class="pill">${s.courseIds ? s.courseIds.length : 0}</span></td>
                        <td class="col-actions">
                            <button class="btn btn-ghost btn-sm"
                                    onclick="showStudentDetail(${esc(s.id)})">View</button>
                        </td>
                    </tr>`).join('')}
            </tbody>
        </table>`;
}

function renderCourses(courses) {

    const host = document.getElementById('courses-table');

    if (courses instanceof Error) { host.innerHTML = errorState(courses); return; }
    if (!courses.length)          { host.innerHTML = emptyState('No courses yet. Create one above.'); return; }

    host.innerHTML = `
        <table>
            <thead>
                <tr><th>ID</th><th>Code</th><th>Title</th><th>Instructor</th><th>Students</th><th></th></tr>
            </thead>
            <tbody>
                ${courses.map(c => `
                    <tr>
                        <td class="col-id">${esc(c.id)}</td>
                        <td><span class="pill pill-code">${esc(c.courseCode)}</span></td>
                        <td>${esc(c.title)}</td>
                        <td>${c.instructorName
                                ? esc(c.instructorName)
                                : '<span class="pill pill-muted">unassigned</span>'}</td>
                        <td><span class="pill">${c.studentIds ? c.studentIds.length : 0}</span></td>
                        <td class="col-actions">
                            <button class="btn btn-ghost btn-sm"
                                    onclick="showCourseDetail(${esc(c.id)})">View</button>
                        </td>
                    </tr>`).join('')}
            </tbody>
        </table>`;
}

function renderInstructors(instructors) {

    const host = document.getElementById('instructors-table');

    if (instructors instanceof Error) { host.innerHTML = errorState(instructors); return; }
    if (!instructors.length)          { host.innerHTML = emptyState('No instructors yet. Create one above.'); return; }

    host.innerHTML = `
        <table>
            <thead>
                <tr><th>ID</th><th>Name</th><th>Email</th><th>Courses</th><th></th></tr>
            </thead>
            <tbody>
                ${instructors.map(i => `
                    <tr>
                        <td class="col-id">${esc(i.id)}</td>
                        <td>${esc(i.name)}</td>
                        <td>${esc(i.email)}</td>
                        <td><span class="pill">${i.courseIds ? i.courseIds.length : 0}</span></td>
                        <td class="col-actions">
                            <button class="btn btn-ghost btn-sm"
                                    onclick="showInstructorDetail(${esc(i.id)})">View</button>
                        </td>
                    </tr>`).join('')}
            </tbody>
        </table>`;
}

function renderTaughtCourses(courses) {

    const host = document.getElementById('taught-result');

    if (!courses.length) {
        host.innerHTML = emptyState('This instructor teaches no courses yet.');
        return;
    }

    host.innerHTML = courses.map(c => `
        <div class="nested-card">
            <div class="nested-head">
                <span class="pill pill-code">${esc(c.courseCode)}</span>
                <span class="nested-title">${esc(c.title)}</span>
            </div>
            <p class="nested-desc">${esc(c.description)}</p>
        </div>`).join('');
}

/* ------------------------------------------------------------
   dropdowns
   ------------------------------------------------------------ */

function fillSelect(source, items, labelFor) {

    document.querySelectorAll(`select[data-source="${source}"]`).forEach(select => {

        if (items instanceof Error || !items.length) {
            select.innerHTML = `<option value="">-- none available --</option>`;
            return;
        }

        const previous = select.value;

        select.innerHTML = items
            .map(item => `<option value="${esc(item.id)}">${esc(labelFor(item))}</option>`)
            .join('');

        // keep the user's choice across a refresh where possible
        if (previous && items.some(item => String(item.id) === previous)) {
            select.value = previous;
        }
    });
}

/* ------------------------------------------------------------
   modal + detail views
   ------------------------------------------------------------ */

function initModal() {

    document.querySelectorAll('[data-close-modal]').forEach(el => {
        el.addEventListener('click', closeModal);
    });

    document.addEventListener('keydown', (event) => {
        if (event.key === 'Escape') closeModal();
    });
}

function openModal(title, endpoint, bodyHtml) {
    document.getElementById('modal-title').textContent = title;
    document.getElementById('modal-endpoint').textContent = endpoint;
    document.getElementById('modal-body').innerHTML = bodyHtml;
    document.getElementById('modal').hidden = false;
}

function closeModal() {
    document.getElementById('modal').hidden = true;
}

async function showStudentDetail(id) {
    try {
        const student = await API.students.detail(id);

        const body = student.courses.length
            ? `<p class="detail-section-title">Enrolled courses (${student.courses.length})</p>
               ${student.courses.map(courseCard).join('')}`
            : emptyState('Not enrolled in any course yet.');

        openModal(`${student.name} - ${student.email}`, `GET /api/students/${id}`, body);

    } catch (error) {
        toast('error', 'Could not load student', error.message);
    }
}

async function showCourseDetail(id) {
    try {
        const course = await API.courses.detail(id);

        openModal(
            `${course.courseCode} - ${course.title}`,
            `GET /api/courses/${id}`,
            courseCard(course)
        );

    } catch (error) {
        toast('error', 'Could not load course', error.message);
    }
}

async function showInstructorDetail(id) {
    try {
        const instructor = await API.instructors.detail(id);

        const body = instructor.courses.length
            ? `<p class="detail-section-title">Courses taught (${instructor.courses.length})</p>
               ${instructor.courses.map(courseCard).join('')}`
            : emptyState('This instructor teaches no courses yet.');

        openModal(`${instructor.name} - ${instructor.email}`, `GET /api/instructors/${id}`, body);

    } catch (error) {
        toast('error', 'Could not load instructor', error.message);
    }
}

/**
 * Renders a CourseDetailDto: course info + nested instructor + nested students.
 * Shared by all three detail views since they all embed this same shape.
 */
function courseCard(course) {

    const instructor = course.instructor
        ? `<span class="chip">${esc(course.instructor.name)}
               <small>${esc(course.instructor.email)}</small></span>`
        : `<span class="pill pill-muted">no instructor assigned</span>`;

    const students = (course.students && course.students.length)
        ? course.students.map(s =>
            `<span class="chip">${esc(s.name)} <small>${esc(s.email)}</small></span>`).join('')
        : `<span class="pill pill-muted">no students enrolled</span>`;

    return `
        <div class="nested-card">
            <div class="nested-head">
                <span class="pill pill-code">${esc(course.courseCode)}</span>
                <span class="nested-title">${esc(course.title)}</span>
            </div>
            <p class="nested-desc">${esc(course.description)}</p>

            <div class="nested-row">
                <span class="nested-label">Instructor</span>
                <div class="chip-list">${instructor}</div>
            </div>

            <div class="nested-row">
                <span class="nested-label">Students (${course.students ? course.students.length : 0})</span>
                <div class="chip-list">${students}</div>
            </div>
        </div>`;
}
