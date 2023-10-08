export const validate = {
    phone(_, value) {
        value = value.trim();
        let reg = /^(?:(?:\+|00)1)\d{10}$/;
        if (value.length === 0) return Promise.reject(new Error('Required: Phone Number!'));
        if (!reg.test(value)) return Promise.reject(new Error('Invalid Phone NUmber!'));
        return Promise.resolve();
    },
    code(_, value) {
        value = value.trim();
        let reg = /^\d{6}$/;
        if (value.length === 0) return Promise.reject(new Error('Required: Verification Number!'));
        if (!reg.test(value)) return Promise.reject(new Error('Invalid Verification NUmber!'));
        return Promise.resolve();
    }
};