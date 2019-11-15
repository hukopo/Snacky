import * as React from "react";

import cn from './Card.less';

export class Card extends React.Component {
    render() {
        return (
            <div className={cn("card")}>
                <div className={cn("header")}>Card</div>
                <div>text sample text text sample text text sample text text sample text text sample text text sample text text sample text text sample text text sample text</div>
                <div className={cn("tags")}>
                    tag1 tag2
                    <span className={cn("add")} onClick={()=> alert("add tag modal")}>+</span>
                </div>
            </div>
        );
    }
}