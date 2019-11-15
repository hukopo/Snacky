import * as React from "react";

import cn from './Board.less';
import { Card } from "../Card/Card";

export class Board extends React.Component {
    render() {
        return (
            <div className={cn("board")}>
                <input/>
                <div className={cn("cards")}>
                <Card/>
                <Card/>
                <Card/>
                <Card/>
                <Card/>
                <Card/>
                <Card/>
                <Card/>
                <Card/>
                <Card/>
                <Card/>

                </div>
            </div>
        );
    }
}