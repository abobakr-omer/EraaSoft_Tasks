/* ============================================================
   api.js - thin wrapper over the REST endpoints
   ============================================================ */

const API = (() => {

    const BASE = '/api';

    /**
     * Performs the request and normalises the error shape.
     * The backend returns {"error": "..."} for 404/409,
     * a field->message map for validation errors (400),
     * and a plain string for anything else.
     */
    async function request(method, url, body) {

        const options = { method, headers: {} };

        if (body !== undefined) {
            options.headers['Content-Type'] = 'application/json';
            options.body = JSON.stringify(body);
        }

        let response;
        try {
            response = await fetch(url, options);
        } catch (networkError) {
            throw new Error('Cannot reach the server. Is the application running?');
        }

        const text = await response.text();
        let payload = null;

        if (text) {
            try {
                payload = JSON.parse(text);
            } catch (e) {
                payload = text;
            }
        }

        if (!response.ok) {
            throw new Error(extractMessage(payload, response.status));
        }

        return payload;
    }

    function extractMessage(payload, status) {

        if (typeof payload === 'string' && payload.trim()) {
            return payload;
        }

        if (payload && typeof payload === 'object') {

            if (payload.error) {
                return payload.error;
            }

            // validation errors: { "email": "Email must be valid", ... }
            const fieldMessages = Object.entries(payload)
                .map(([field, message]) => `${field}: ${message}`);

            if (fieldMessages.length) {
                return fieldMessages.join(' | ');
            }
        }

        return `Request failed with status ${status}`;
    }

    return {
        students: {
            all:      ()                      => request('GET',  `${BASE}/students/all-students`),
            detail:   (id)                    => request('GET',  `${BASE}/students/${id}`),
            create:   (data)                  => request('POST', `${BASE}/students/create-student`, data),
            register: (studentId, courseId)   => request('PUT',  `${BASE}/students/${studentId}/register-course/${courseId}`)
        },
        courses: {
            all:      ()                      => request('GET',  `${BASE}/courses/all-courses`),
            detail:   (id)                    => request('GET',  `${BASE}/courses/${id}`),
            create:   (data)                  => request('POST', `${BASE}/courses/create-course`, data),
            assign:   (courseId, instructorId)=> request('PUT',  `${BASE}/courses/${courseId}/assign-instructor/${instructorId}`)
        },
        instructors: {
            all:      ()                      => request('GET',  `${BASE}/instructors/all-instructors`),
            detail:   (id)                    => request('GET',  `${BASE}/instructors/${id}`),
            create:   (data)                  => request('POST', `${BASE}/instructors/create-instructor`, data),
            taught:   (id)                    => request('GET',  `${BASE}/instructors/${id}/courses-taught`)
        }
    };
})();
