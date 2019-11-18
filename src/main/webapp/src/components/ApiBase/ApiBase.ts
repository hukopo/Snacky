import { CardDto } from "../../types/CardDto";
//пока не втягивал
export interface IApiClient {
  loadCards: () => Promise<CardDto[]>;
  createCard: (card: CardDto) => Promise<boolean>;
}

export class ApiClient implements IApiClient {
  public loadCards = async (): Promise<CardDto[]> => {
    const response = await fetch("/getAll");
    console.log(`upload cards with code ${response.status}`);
    if (!response.ok) {
      throw new Error(`load cards fail with status ${response.status}`);
    }
    const data = await response.json();
    return data as CardDto[];
  };

  public createCard = async (card: CardDto): Promise<boolean> => {
    const response = await fetch("/add", { body: card, method: "POST" });
    console.log(`card be created with code ${response.status}`);
    if (!response.ok) {
      return false;
    }
    return true;
  };
}
