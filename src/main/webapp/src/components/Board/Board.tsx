import * as React from "react";
import { Card } from "../Card/Card";
import cn from "./Board.less";

interface BoardsState {
  cards: CardDto[];
}
interface BoardProps {
  onEditCard: (card: CardDto) => void
}
export class Board extends React.Component<BoardProps, BoardsState> {
  state = {
    cards: [] as CardDto[]
  };

  componentDidMount = async () => {
    await this.updateBoard();
    setInterval(async () => await this.updateBoard(), 5 * 1000);
  };

  loadCards = async (): Promise<CardDto[]> => {
    const response = await fetch("/cards/getAll?skip=0&take=1000");
    console.log(`upload cards with code ${response.status}`);
    if (!response.ok) {
      throw new Error(`load cards fail with status ${response.status}`);
    }
    const data = await response.json();
    return data as CardDto[];
  };

  updateBoard = async () => this.setState({ cards: await this.loadCards() });

  render() {
    return (
      <div className={cn("board")}>
        {this.state.cards.length === 0 ? (
          "loading..."
        ) : (
          <div className={cn("cards")}>
            {this.state.cards.map(card => (
              <Card key={card.title} card={card} onEditCard={this.props.onEditCard} />
            ))}
          </div>
        )}
      </div>
    );
  }
}
