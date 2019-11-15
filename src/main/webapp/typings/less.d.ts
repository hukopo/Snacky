declare module "*.less" {
    declare const styles: { [className: string]: string } & ((
        ...args: Array<string, string[], { [name: string]: boolean }>
    ) => string);

    export default styles;
}
