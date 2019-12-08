import * as React from "react";
import cn from "./Card.less";

interface CardProps {
  card: CardDto;
  onEditCard: (card: CardDto) => void;
}

export class Card extends React.Component<CardProps> {
  render() {
    const card = this.props.card;
    return (
      <div className={cn("card")}>
        <div className={cn("controls")}>
          <div className={cn("control")} onClick={this.onEdit}>🖊</div>
          <div className={cn("control")} onClick={this.onTrash}>🗑️</div>
        </div>
        <div>owner: {card.creator.userName}</div>
        <div className={cn("header")}>title: {card.title}</div>
        <div>description: {card.description.content}</div>
        <div>members: {card.members.map(m => ` ${m.userName}`)}</div>
        <div className={cn("tags")}>tags: {card.tags.map(t => t.name)}</div>
        <div>
          период:
          {card.startDate} - {card.endDate}
        </div>
      </div>
    );
  }

  onTrash = async () => {
    await fetch(`/cards/${this.props.card.id}/delete`, { method: "DELETE" });
  };

  onEdit = () => {
      this.props.onEditCard(this.props.card);
  }
}
