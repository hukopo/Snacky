import * as React from "react";
import cn from "./Card.less";

interface CardProps {
  card: CardDto;
}

export class Card extends React.Component<CardProps> {
  render() {
    const card = this.props.card;
    return (
      <div className={cn("card")}>
        <div className={cn("controls")}>
          <span className={cn("control")} onClick={this.onEdit}>🖊</span>
          <span className={cn("control")} onClick={this.onTrash}>🗑️</span>
        </div>
        <div>owner: {card.creator.userName}</div>
        <div className={cn("header")}>{card.title}</div>
        <div>{card.description.content}</div>
        <div className={cn("tags")}>
          tags: {card.tags.map(t => t.name)}
          {/* <span className={cn("add")} onClick={() => alert("add tag modal")}>
            +
          </span> */}
        </div>
        <div>
          период:
          {card.startDate} - {card.endDate}
        </div>
      </div>
    );
  }

  onTrash = async () => {
    await fetch(`/cards/${this.props.card.id}/delete`, { method: 'DELETE' })
  };

  onEdit = () => {}
}
