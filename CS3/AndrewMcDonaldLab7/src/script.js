const app = new Vue({
    el: '#app',
    data: {
        amountInCart: 0,
        selected: 0,
        variants: [
            {
                id: 0,
                price: 49.99,
                color: 'black',
                hex: '#000',
                quantity: 10,
                image: '../assets/black.png',
            },
            {
                id: 1,
                price: 59.99,
                color: 'green',
                hex: '#175602',
                quantity: 0,
                image: '../assets/green.png',
            },
        ],
    },
    methods: {
        updateProduct(index) {
            this.selected = index;
        },
        addToCart() {
            this.amountInCart += 1;
            this.variants[this.selected].quantity -= 1;
        },
    },
    computed: {
        productName() {
            const color = this.variants[this.selected].color;
            return `${color.charAt(0).toUpperCase() + color.slice(1)} shoes`;
        },
        price() {
            return `$${this.variants[this.selected].price}`;
        },
        inStock() {
            return this.variants[this.selected].quantity;
        },
        image() {
            return this.variants[this.selected].image;
        },
    },
});
