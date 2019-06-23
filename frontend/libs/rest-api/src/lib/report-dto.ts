export interface ReportItemDto {
  id: number;
  artifact: string;
  timestamp: number;
  reportUuid: string;
}

export interface ReportDto {
  items: ReportItemDto[];
}
