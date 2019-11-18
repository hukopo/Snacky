import * as React from "react";
import { Card } from "../Card/Card";
import cn from "./Board.less";

interface BoardsState {
  cards: CardDto[];
}

export class Board extends React.Component<{}, BoardsState> {
  state = {
    cards: [] as CardDto[]
  };

  componentDidMount = async () => {
    const cards = await this.loadCards();
    this.setState({ cards: cards });
  };

  loadCards = async (): Promise<CardDto[]> => {
    const response = await fetch("/cards/getAll");
    console.log(`upload cards with code ${response.status}`);
    if (!response.ok) {
      throw new Error(`load cards fail with status ${response.status}`);
    }
    const data = await response.json();
    return data as CardDto[];
  };

  render() {
    return (
      <div className={cn("board")}>
        <input />
        {this.state.cards.length === 0 ? (
          "loading..."
        ) : (
          <div className={cn("cards")}>
            {this.state.cards.map(card => (
              <Card key={card.title} card={card} />
            ))}
          </div>
        )}
      </div>
    );
  }
}
